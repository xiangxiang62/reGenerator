import type {ProFormInstance} from '@ant-design/pro-components';
import {
  ProCard,
  ProFormSelect,
  ProFormText,
  ProFormTextArea,
  StepsForm,
} from '@ant-design/pro-components';
import {Card, message, UploadFile} from 'antd';
import React, {useEffect, useRef, useState} from 'react';
import {ProFormItem} from "@ant-design/pro-form";
import PictureUploader from "@/components/PictureUploader";
import FileUploader from "@/components/FileUploader";
import {
  addGeneratorUsingPost,
  editGeneratorUsingPost,
  getGeneratorVoByIdUsingGet
} from "@/services/backend/generatorController";
import {history} from "@umijs/max";
import {useSearchParams} from "@@/exports";
import {COS_HOST} from "@/constants";
import ModelConfigForm from "@/pages/Generator/Add/components/ModelConfigForm";
import FileConfigForm from "@/pages/Generator/Add/components/FileConfigForm";
import GeneratorMaker from "@/pages/Generator/Add/components/GeneratorMaker";


/**
 * 创建生成器页面
 *
 * @constructor
 */
const GeneratorAddPage: React.FC = () => {
  const [searchPrams] = useSearchParams();
  const id = searchPrams.get("id");
  const [oldData, setOldData] = useState<API.GeneratorEditRequest>();
  const formRef = useRef<ProFormInstance>();
  // 记录表单数据
  const [basicInfo, setBasicInfo] = useState<API.GeneratorEditRequest>();
  const [modelConfig, setModelConfig] = useState<API.ModelConfig>();
  const [fileConfig, setFileConfig] = useState<API.FileConfig>();

  /**
   * 加载数据
   */
  const loadData = async () => {
    if (!id) {
      return;
    }

    try {
      const res = await getGeneratorVoByIdUsingGet({
        id
      });
      // 处理文件路径
      if (res.data) {
        const {distPath} = res.data ?? {};
        if (distPath) {
          // @ts-ignore
          res.data.distPath = [
            {
              uid: id,
              name: '文件' + id,
              status: 'done',
              url: COS_HOST + distPath,
              response: distPath
            } as UploadFile,
          ];
        }
        setOldData(res.data);
      }
    } catch (error: any) {

    }
  };

  useEffect(() => {
    if (!id) {
      return;
    }
    loadData()
  }, [id]);

  const doAdd = async (values: API.GeneratorAddRequest) => {
    // 调用接口
    try {
      const res = await addGeneratorUsingPost(values);
      if (res.data) {
        message.success("创建成功");
        history.push(`/generator/detail/${res.data}`)
      }
    } catch (error: any) {
      message.error("创建失败：" + error.message);
    }
  }


  const doUpdata = async (values: API.GeneratorEditRequest) => {
    // 调用接口
    try {
      const res = await editGeneratorUsingPost(values);
      if (res.data) {
        message.success("更新成功");
        history.push(`/generator/detail/${id}`)
      }
    } catch (error: any) {
      message.error("更新失败：" + error.message);
    }
  }

  const doSumit = async (values: API.GeneratorAddRequest) => {
    // 数据转换
    if (!values.fileConfig) {
      values.fileConfig = {}
    }
    if (!values.modelConfig) {
      values.modelConfig = {}
    }
    // 文件列表转换为 url
    if (values.distPath && values.distPath.length > 0) {
      // @ts-ignore
      values.distPath = values.distPath[0].response;
    }

    // 调用接口
    if (id){
      await doUpdata({
        id,
        ...values
      });
    }else {
      await doAdd(values);
    }
  }

  return (
    <ProCard>
      {/*// 创建或者已加载要更新的数据时，不渲染表单，顺利填充默认值*/}
      {
        (!id || oldData) && (
          <StepsForm<API.GeneratorAddRequest | API.GeneratorEditRequest>
            formRef={formRef}
            onFinish={doSumit}
            formProps={{
              initialValues: oldData,
            }}
          >
            <StepsForm.StepForm
              name="base"
              title="基本信息"
              onFinish={async (values) => {
                setBasicInfo(values);
                return true;
              }}
            >
              <ProFormText name="name" label="名称" placeholder="请输入名称"/>
              <ProFormTextArea name="description" label="描述" placeholder="请输入描述"/>
              <ProFormText name="basePackage" label="基础包" placeholder="请输入基础包"/>
              <ProFormText name="version" label="版本" placeholder="请输入版本"/>
              <ProFormText name="author" label="作者" placeholder="请输入作者"/>
              <ProFormSelect label="标签" mode="tags" name="tags" placeholder="请输入标签列表"/>
              <ProFormItem label="图片" name="picture">
                <PictureUploader biz="generator_picture"/>
              </ProFormItem>
            </StepsForm.StepForm>

            <StepsForm.StepForm
              name="modelConfig"
              title="模型配置"
              onFinish={async (values) => {
              setModelConfig(values);
              return true;
            }}>
              <ModelConfigForm formRef={formRef} oldData={oldData} />
            </StepsForm.StepForm>

            <StepsForm.StepForm
              name="fileConfig"
              title="文件配置"
              onFinish={async (values) => {
              setFileConfig(values);
              return true;
            }}>
              <FileConfigForm formRef={formRef} oldData={oldData} />
            </StepsForm.StepForm>

            <StepsForm.StepForm
              name="dist"
              title="生成器文件"
            >
              <ProFormItem label="产物包" name="distPath">
                <FileUploader biz="generator_dist" description="请上传生成器文件压缩包"/>
              </ProFormItem>
              <GeneratorMaker meta={{
                ...basicInfo,
                ...modelConfig,
                ...fileConfig
              }}/>
            </StepsForm.StepForm>
          </StepsForm>
        )
      }
    </ProCard>
  );
};

export default GeneratorAddPage;
