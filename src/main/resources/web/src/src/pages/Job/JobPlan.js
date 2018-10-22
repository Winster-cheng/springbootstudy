import React, {PureComponent} from 'react';
import {connect} from 'dva';
import {Row, Col, Button, Form, Input, Card, Tooltip} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import GeneralTable from '@/components/GeneralTable';
import styles from './JobPlan.less';

const {Item} = Form;

@connect (({job, loading}) => ({
  jobList: job.jobList,
  loading: loading.models.job,
}))
@Form.create ()
class JobPlan extends PureComponent {
  state = {
    formValues: {}
  };

  scroll = {x: false};

  columns = [
    {title: '任务名称', dataIndex: 'jobName',width: '33%',render: text => 
      <Tooltip title={text}>
        <span className={styles.tdEllisps}>{text}</span>
      </Tooltip>
      },
    {title: '修改日期', dataIndex: 'updateDate',sorter: true,width: '18%'},
    {title: '任务类型', dataIndex: 'planType',width: '16%'},
    {title: '责任人', dataIndex: 'responser',width: '18%'},
    {title: '调度类型', dataIndex: 'jobType',width: '15%'},
  ];

  componentDidMount () {
    const {dispatch} = this.props;
    dispatch ({
      type: 'job/fetchJobs',
    });
  }

  handleSearch = e => {
    e.preventDefault ();

    const {dispatch, form} = this.props;

    form.validateFields ((err, fieldsValue) => {
      if (err) return;
      console.log(fieldsValue)

      this.setState ({
        formValues: fieldsValue,
      });

      // dispatch ({
      //   type: 'alarmList/fetch',
      //   payload: values,
      // });
    });
  };

  handleFormReset = () => {};

  renderForm = () => {
    const {getFieldDecorator} = this.props.form;
    return (
      <Form onSubmit={this.handleSearch} layout="inline">
        <Row
          gutter={{md: 8, lg: 24, xl: 48}}
          style={{display: 'flex', alignItems: 'center'}}
        >
          <Col md={6} sm={24}>
            <Item label="脚本名称">
              {getFieldDecorator ('searchJobName') (
                <Input placeholder="请输入" />
              )}
            </Item>
          </Col>
          <Col md={8} sm={24}>
            <span className="SubmitButtons">
              <Button type="primary" htmlType="submit">
                查询
              </Button>
              <Button style={{marginLeft: 8}} onClick={this.handleFormReset}>
                重置
              </Button>
            </span>
          </Col>
        </Row>
      </Form>
    );
  };

  handleGeneralTableChange = () => {};

  render () {
    const {loading, jobList} = this.props;
    return (
      <PageHeaderWrapper>
        <Card bordered={false}>
          <div className={styles.fixedHeightTableList}>
            <div className="CommonTableList-Form">{this.renderForm ()}</div>
            <GeneralTable
              rowKey="jobId"
              scroll={this.scroll}
              loading={loading}
              data={jobList}
              columns={this.columns}
              onChange={this.handleGeneralTableChange}
            />
          </div>
        </Card>
      </PageHeaderWrapper>
    );
  }
}
export default JobPlan;
