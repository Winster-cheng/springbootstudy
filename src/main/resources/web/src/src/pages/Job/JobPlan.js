import React, {PureComponent} from 'react';
import {connect} from 'dva';
import {Row, Col, Button, Form, Input, Card, Tooltip} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import GeneralTable from '@/components/GeneralTable';
import GraphFlow from '@/components/GraphFlow';
import styles from './JobPlan.less';

const {Item} = Form;

@connect (({job, loading}) => ({
  jobList: job.jobList,
  loading: loading.models.job,
}))
@Form.create ()
class JobPlan extends PureComponent {
  state = {
    formValues: {},
    showGraphContainer: "hide"
  };

  graphContainerRight = {
    show: "33px",
    hide: ""
  }

  scroll = {x: false};

  fixedWidth = {
    jobName: '',
    updateDate: '',
    planType: '',
    responser: '',
    jobType: '',
  };

  columns = [
    {
      title: '任务名称',
      dataIndex: 'jobName',
      percent: 33,
      render: (text, {jobId}) => this.renderTooltip (text, 'jobName', jobId),
    },
    {
      title: '修改日期',
      dataIndex: 'updateDate',
      sorter: true,
      percent: 18,
      render: text => this.renderTooltip (text, 'updateDate'),
    },
    {
      title: '任务类型',
      dataIndex: 'planType',
      percent: 16,
      render: text => this.renderTooltip (text, 'planType'),
    },
    {
      title: '责任人',
      dataIndex: 'responser',
      percent: 18,
      render: text => this.renderTooltip (text, 'responser'),
    },
    {
      title: '调度类型',
      dataIndex: 'jobType',
      // percent: 15,
      render: text => this.renderTooltip (text, 'jobType'),
    },
  ];

  componentDidMount () {
    const {dispatch} = this.props;
    dispatch ({
      type: 'job/fetchJobs',
    });
  }

  renderTooltip = (text, dataIndex, jobId) => (
    <Tooltip title={text}>
      {jobId
        ? <a
          href="javascript:;"
          style={{maxWidth: `${this.fixedWidth[dataIndex]}px`}}
          onClick={e => this.showGraph (e,jobId)}
          className={styles.tdEllisps}
        >
          {text}
        </a>
        : <span
          href="javascript:;"
          style={{maxWidth: `${this.fixedWidth[dataIndex]}px`}}
          className={styles.tdEllisps}
        >
          {text}
        </span>}
    </Tooltip>
  );

  showGraph = (e,jobId) => {
    e.stopPropagation();
    this.setState({
      showGraphContainer: "show"
    })
  };

  handleSearch = e => {
    e.preventDefault ();

    const {dispatch, form} = this.props;

    form.validateFields ((err, fieldsValue) => {
      if (err) return;
      console.log (fieldsValue);

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

  autoFooter = () => {
    const { jobList = {} } = this.props;
    const { list = [] } = jobList;
    if(list.length > 0 && list.length < 11){
      const autoFooterHeight = 59 * (10 - list.length);
      return <div style={{height: `${autoFooterHeight}px`}}>
        <div style={{height: "100%",width: `${parseInt(this.fixedWidth.jobName,10)+33}px`,borderRight: "1px solid #e8e8e8"}} />
      </div>
    }
      return false;
  }

  hideGraphContainer = () => {
    this.setState({
      showGraphContainer: "hide"
    })
  }

  render () {
    const {loading, jobList = {}} = this.props;
    const tableFullWidth =
      document.body.clientWidth -
      (document.getElementsByClassName ('ant-layout-sider')[0]
        ? document
            .getElementsByClassName ('ant-layout-sider')[0]
            .style.width.replace ('px', '')
        : 0) - 48 - 64;
    this.columns = this.columns.map (item => {
      if(item.dataIndex === "jobType"){// 最后一列 宽度取rest
        delete this.fixedWidth.jobType;
        item.width = tableFullWidth - Object.values(this.fixedWidth).reduce((pre,value=0) => pre + value,0);
        this.fixedWidth[item.dataIndex] = `${item.width - 32}`;
      }else{
        item.width = Math.floor(item.percent / 100 * tableFullWidth);
        this.fixedWidth[item.dataIndex] = `${item.width - 32}`;
      }
      return item;
    });
    const { jobName = 0 } = this.fixedWidth;
    const maskWidthNumber = tableFullWidth - jobName - 36;
    const maskWidth = `${maskWidthNumber}px`;
    this.graphContainerRight.hide = `-${maskWidthNumber + 33}px`
    return (
      <PageHeaderWrapper>
        <Card bordered={false} onClick={this.hideGraphContainer}>
          <div className={styles.fixedHeightTableList}>
            <div onClick={e => e.stopPropagation()} style={{right:`${this.graphContainerRight[this.state.showGraphContainer]}`,width: maskWidth}} className={styles.graphContainer}>
              <GraphFlow />
            </div>
            <div className="CommonTableList-Form">{this.renderForm ()}</div>
            <GeneralTable
              rowKey="jobId"
              bordered
              footer={this.autoFooter}
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
