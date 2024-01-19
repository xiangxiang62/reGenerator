import {
  PageContainer,
} from '@ant-design/pro-components';
import {Button, Card, Col, Image, message, Row, Space, Tabs, Tag, Typography} from 'antd';
import React, {useEffect, useState} from 'react';
import {
  downloadGeneratorByIdUsingGet,
  getGeneratorVoByIdUsingGet
} from "@/services/backend/generatorController";
import {useParams} from "@@/exports";
import {DownloadOutlined, EditOutlined} from "@ant-design/icons";
import moment from "moment";
import AuthorInfo from "@/pages/Generator/Detail/components/AuthorInfo";
import ModelConfig from "@/pages/Generator/Detail/components/ModelConfig";
import FileConfig from "@/pages/Generator/Detail/components/FileConfig";
import {saveAs} from "file-saver";
import {useModel} from "@@/plugin-model";
import {Link} from "umi";


/**
 * 生成器详情页
 *
 * @constructor
 */
const GeneratorDetailPage: React.FC = () => {
  const {id} = useParams();
  const [loading, setLoading] = useState<boolean>(true);
  const [data, setData] = useState<API.GeneratorVO>({});
  const { initialState } = useModel('@@initialState');
  const { currentUser } = initialState ?? {};
  const my = data?.userId === currentUser?.id;


  const loadData = async () => {
    if (!id) {
      return;
    }

    setLoading(true);
    try {
      const res = await getGeneratorVoByIdUsingGet({
        id
      });
      // 处理文件路径
      setData(res.data ?? {});
    } catch (error: any) {
      message.error("获取数据失败：" + error.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    if (!id) {
      return;
    }
    loadData()
  }, [id]);


  /**
   * 标签列表
   * @param tags
   */
  const tagListView = (tags?: string[]) => {
    if (!tags) {
      return <></>
    }

    return <div style={{marginBottom:16}}>
      {
        tags.map((tag) => (
          <Tag key={tag}>{tag}</Tag>
        ))
      }
    </div>
  }

  /**
   * 下载按钮
   */
  const downloadButton = data.distPath && currentUser && (
    <Button
      icon={<DownloadOutlined />}
      onClick={async () => {
        const blob = await downloadGeneratorByIdUsingGet(
          {
            id: data.id,
          },
          {
            responseType: 'blob',
          },
        );
        // 使用 file-saver 来保存文件
        const fullPath = data.distPath || '';
        saveAs(blob, fullPath.substring(fullPath.lastIndexOf('/') + 1));
      }}
    >
      下载
    </Button>
  );


  /**
   * 编辑按钮
   */
  const editButton = my && (
    <Link to={`/generator/update?id=${data.id}`}>
      <Button icon={<EditOutlined />}>编辑</Button>
    </Link>
  );


  return (
    <PageContainer title={<></>} loading={loading}>
      <Card>
        <Row justify="space-between" gutter={[32, 32]}>
          <Col flex="auto">
            <Space size="large" align="center">
              <Typography.Title level={4}>{data.name}</Typography.Title>
              {tagListView(data.tags)}
            </Space>
            <Typography.Paragraph>{data.description}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">
              创建时间：{moment(data.createTime).format('YYYY-MM-DD hh:mm:ss')}
            </Typography.Paragraph>
            <Typography.Paragraph type="secondary">基础包：{data.basePackage}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">版本：{data.version}</Typography.Paragraph>
            <Typography.Paragraph type="secondary">作者：{data.author}</Typography.Paragraph>
            <div style={{marginBottom: 24}}/>
            <Space size="middle">
              <Button type="primary">立即使用</Button>
              {downloadButton}
              {editButton}
            </Space>
          </Col>
          <Col flex="320px">
            <Image src={data.picture}/>
          </Col>
        </Row>
      </Card>
      <div style={{marginBottom: 24}}/>
      <Card>
        <Tabs
          size="large"
          defaultActiveKey={'fileConfig'}
          onChange={() => {}}
          items={[
            {
              key: 'fileConfig',
              label: '文件配置',
              children: <FileConfig data={data} />,
            },
            {
              key: 'modelConfig',
              label: '模型配置',
              children: <ModelConfig data={data} />,
            },
            {
              key: 'userInfo',
              label: '作者信息',
              children: <AuthorInfo data={data} />,
            },
          ]}
        />
      </Card>
    </PageContainer>
  );
};

export default GeneratorDetailPage;
