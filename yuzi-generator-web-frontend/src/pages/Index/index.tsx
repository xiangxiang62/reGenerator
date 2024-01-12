import {PageContainer, ProFormText, QueryFilter} from "@ant-design/pro-components";
import React, {useEffect, useRef, useState} from 'react';
import {listGeneratorVoByPageUsingPost} from "@/services/backend/generatorController";
import {Avatar, Card, Flex, Image, Input, List, message, Space, Tabs, Tag, Typography} from "antd";
import {ProFormSelect} from "@ant-design/pro-form/lib";
import moment from "moment";
import {UserOutlined} from "@ant-design/icons";

/**
 * 默认分页参数
 */
const DEFAULT_PAGE_PARAMS: PageRequest = {
  current: 1,
  pageSize: 4,
  sortField: 'createTime',
  sortOrder: 'descend',
}

const IndexPage: React.FC = () => {

  const [loading, setLoading] = useState<boolean>(true);
  const [dataList, setDataList] = useState<API.GeneratorVO>();
  const [total, setTotal] = useState<number>();
  // 搜索条件
  const [searchParms, setSearchParms] = useState<API.GeneratorQueryRequest>(
    {
      ...DEFAULT_PAGE_PARAMS,
    }
  )

  /**
   * 搜素
   */
  const doSearch = async () => {
    setLoading(true);
    try {
      const res = await listGeneratorVoByPageUsingPost(searchParms);
      // @ts-ignore
      setDataList(res.data?.records ?? []);
      setTotal(Number(res.data?.total) ?? 0)
    } catch (error: any) {
      message.error("获取数据失败：" + error.message)
    }
    setLoading(false)
  }

  useEffect(() => {
    doSearch();
  }, [searchParms]);

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

  // @ts-ignore
  return <PageContainer title={<></>}>
    <Flex justify={"center"}>
      <Input.Search
        style={{
          width: '40vw',
          minWidth:320,
        }}
        placeholder="请搜索生成器"
        allowClear
        enterButton="搜索"
        size="large"
        onChange={(e) => {
          searchParms.searchText = e.target.value
        }}
        onSearch={(value: string) => {
          setSearchParms({
            ...DEFAULT_PAGE_PARAMS,
            searchText: value,
          })
        }}
      />
    </Flex>


    <Tabs size={"large"} defaultActiveKey="newest" items={[
      {
        key: 'newest',
        label: '最新',
      },
      {
        key: 'recommend',
        label: '推荐',
      },
    ]} onChange={() => {
    }}/>

    <QueryFilter
      // defaultCollapsed={false}
      span={12}
      labelAlign="left"
      labelWidth="auto"
      style={{padding: '16px 0'}}
      onFinish={async (values: API.GeneratorQueryRequest) => {
        setSearchParms({
          ...DEFAULT_PAGE_PARAMS,
          // @ts-ignore
          ...values,
          searchText: searchParms.searchText
        })
      }}
    >
      <ProFormSelect label="标签" name="tags" mode="tags"/>
      <ProFormText label="名称" name="name"/>
      <ProFormText label="描述" name="description"/>
    </QueryFilter>

    <div style={{ marginBottom: 24 }} />

    <List<API.GeneratorVO>
      rowKey="id"
      loading={loading}
      grid={{
        gutter: 16,
        xs: 1,
        sm: 2,
        md: 3,
        lg: 3,
        xl: 4,
        xxl: 4,
      }}
      dataSource={dataList}
      pagination={{
        current: searchParms.current,
        pageSize: searchParms.pageSize,
        total,
        onChange(current: number, pageSize: number) {
          setSearchParms({
            ...searchParms,
            current,
            pageSize,
          })
        }
      }}
      renderItem={(data) => (
        <List.Item>
          <Card hoverable cover={<Image alt={data.name} src={data.picture}/>}>
            <Card.Meta
              title={<a>{data.name}</a>}
              description={
                <Typography.Paragraph ellipsis={{rows: 2}} style={{height: 44}}>
                  {data.description}
                </Typography.Paragraph>
              }
            />
            {tagListView(data.tags)}
            <Flex justify="space-between" align="center">
              <Typography.Paragraph type="secondary" style={{ fontSize: 12 }}>{moment(data.createTime).fromNow()}</Typography.Paragraph>
              <div>
                <Avatar src={data.user?.userAvatar ?? <UserOutlined/>}/>
              </div>
            </Flex>
          </Card>
        </List.Item>
      )}
    />
  </PageContainer>;
};


export default IndexPage;
