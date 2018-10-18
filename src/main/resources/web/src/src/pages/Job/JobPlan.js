import React, { PureComponent } from 'react';
import { connect } from 'dva';
import { Row, Col, Button, Form, Input, Card, Badge } from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import GeneralTable from '@/components/GeneralTable';
import styles from "./JobPlan.less"

const { Item } = Form;

@connect(({ job, loading }) => ({
  jobList: job.jobList,
  loading: loading.models.job,
}))
@Form.create()
class JobPlan extends PureComponent {
  state = {
  }

  scroll = { x: false };

  columns = [
    { title: '任务名称', dataIndex: 'jobName'},
    { title: '修改日期', dataIndex: 'updateDate'},
    { title: '任务类型', dataIndex: 'planType'},
    { title: '责任人', dataIndex: 'responser'},
    { title: '调度类型', dataIndex: 'jobType'},
  ];

  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'job/fetchJobs',
    });
  }

  handleSearch = () => {
    
  }

  handleFormReset = () => {

  }

  renderForm = () => {
    const { getFieldDecorator } = this.props.form;
    return (
      <Form onSubmit={this.handleSearch} layout="inline">
        <Row gutter={{ md: 8, lg: 24, xl: 48 }}>
          <Col md={8} sm={24}>
            <Item label="请输入脚本名称">
              {getFieldDecorator('searchJobName')(<Input />)}
            </Item>
          </Col>
          <Col md={8} sm={24}>
            <span className="SubmitButtons">
              <Button type="primary" htmlType="submit">
                查询
              </Button>
              <Button style={{ marginLeft: 8 }} onClick={this.handleFormReset}>
                重置
              </Button>
            </span>
          </Col>
        </Row>
      </Form>
    );
  }

  handleGeneralTableChange = () => {
    
  }

  render() {
    const {
      loading,
      jobList,
    } = this.props;
    console.log(jobList)
    return (
      <PageHeaderWrapper>
        <div className={styles.CommonTableList}>
          <div className="CommonTableList-Form">{this.renderForm()}</div>
          <GeneralTable
            rowKey="jobId"
            scroll={this.scroll}
            loading={loading}
            data={jobList}
            columns={this.columns}
            onChange={this.handleGeneralTableChange}
          />
        </div>
      </PageHeaderWrapper>
    );
  }
}
export default JobPlan;
