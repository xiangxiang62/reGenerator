import React, {useState} from 'react';
import {Button, message, Upload,  UploadProps} from "antd";
import { LoadingOutlined} from "@ant-design/icons";
import {
  uploadFileUsingPost
} from "@/services/backend/fileController";
import {COS_HOST} from "@/constants";


interface Props {
  biz: string;
  onChange?: (url:string) => void;
  value?: string;
}

function PlusOoutlined() {
  return null;
}

/**
 * 图片上传组件
 *
 *
 * @constructor
 */
const PictureUploader: React.FC<Props> = (props) => {
  const {biz, value, onChange} = props;
  const [loading, setloading] = useState<boolean>(false);


  const uploadProps: UploadProps = {
    name: 'file',
    multiple: false,
    maxCount: 1,
    listType: "picture-card",
    disabled: loading,
    showUploadList:false,
    customRequest: async (fileObj: any) => {
      setloading(true);
      try {
        const res = await uploadFileUsingPost({
          biz
        }, {}, fileObj.file);
        // 拼接完整路径
        const fullPath = COS_HOST + res.data;
        onChange?.(fullPath ?? '');
        fileObj.onSuccess(res.data);
      } catch (e: any) {
        message.error("上传失败，" + e.message);
        fileObj.onError(e);
      }
      setloading(false);
    },
  };

  const uploadButton = (
    <Button style={{ border: 0, background: 'none'}} type="button">
      {loading ? <LoadingOutlined/> : <PlusOoutlined />}
      <div style={{ marginTop: 8}}>上传</div>
    </Button>
  )

  return (
    <Upload {...uploadProps}>
      {value ? <img src={value} alt="picture" style={{ width: '100%'}} /> : uploadButton}
    </Upload>
  );
};
export default PictureUploader;
