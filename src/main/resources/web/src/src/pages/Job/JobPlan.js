import React, {PureComponent} from 'react';
import {connect} from 'dva';
import {Row, Col, Button, Form, Input, Card, Tooltip} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import GeneralTable from '@/components/GeneralTable';
import GraphFlow from '@/components/GraphFlow';
import GraphCustomTool from '@/components/GraphFlow/GraphCustomTool';
import styles from './JobPlan.less';

const {Item} = Form;
const GraphFlowCustomed = GraphCustomTool()(GraphFlow)

@connect (({job, task, loading}) => ({
  data: job.data,
  loading: loading.models.job,
  graphDependencies: task.graphNodes,
  graphNodesFetchLoading: loading.effects['task/fetchAllGraphNodes'],
}))
@Form.create ()
class JobPlan extends PureComponent {
  state = {
    formValues: {},
    pagination: {
      pageNo: 1,
      timeSortType: '',
    },
    showGraphContainer: 'hide',
    jobId: 0,
  };

  graphContainerRight = {
    show: '33px',
    hide: '',
  };

  scroll = {x: false};

  fixedWidth = {
    name: '',
    gmtModify: '',
    executeRate: '',
    owner: '',
    type: '',
  };

  columns = [
    {
      title: '任务名称',
      dataIndex: 'name',
      percent: 33,
      render: (text, {id}) => this.renderTooltip (text, 'name', id),
    },
    {
      title: '修改日期',
      dataIndex: 'gmtModify',
      sorter: true,
      percent: 18,
      render: text => this.renderTooltip (text, 'gmtModify'),
    },
    {
      title: '任务类型',
      dataIndex: 'type',
      percent: 16,
      render: text => this.renderTooltip (text, 'type'),
    },
    {
      title: '责任人',
      dataIndex: 'owner',
      percent: 18,
      render: text => this.renderTooltip (text, 'owner'),
    },
    {
      title: '调度类型',
      dataIndex: 'executeRate',
      // percent: 15,
      render: text => this.renderTooltip (text, 'executeRate'),
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
          onClick={e => this.showGraph (e, jobId)}
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

  showGraph = (e, jobId) => {
    e.stopPropagation ();
    this.setState ({
      showGraphContainer: 'show',
      jobId,
    });
    this.props.dispatch ({
      type: 'task/fetchAllGraphNodes',
      payload: jobId,
    });
  };

  handleSearch = e => {
    e.preventDefault ();
    const {dispatch, form} = this.props;
    form.validateFields ((err, fieldsValue) => {
      if (err) return;
      this.setState ({
        formValues: fieldsValue,
      });
      dispatch ({
        type: 'job/fetchJobs',
        payload: {
          ...this.state.pagination,
          ...fieldsValue,
        },
      });
    });
  };

  handleFormReset = () => {
    const {form, dispatch} = this.props;
    form.resetFields ();
    this.setState ({
      formValues: {},
    });
    dispatch ({
      type: 'job/fetchJobs',
    });
  };

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
              {getFieldDecorator ('fileName') (<Input placeholder="请输入" />)}
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

  handleGeneralTableChange = (pagination, filters, sorter) => {
    const param = {};
    if (sorter.order) {
      if (sorter.order === 'descend') {
        param.timeSortType = 2;
      } else {
        param.timeSortType = 1;
      }
    } else {
      param.timeSortType = 0;
    }
    param.pageNo = pagination.current;
    this.setState ({
      pagination: param,
    });
    this.props.dispatch ({
      type: 'job/fetchJobs',
      payload: {
        ...param,
        ...this.state.formValues,
      },
    });
  };

  autoFooter = () => {
    const {data = {}} = this.props;
    const {list = []} = data;
    if (list.length > 0 && list.length < 11) {
      const autoFooterHeight = 59 * (10 - list.length);
      return (
        <div style={{height: `${autoFooterHeight}px`}}>
          <div
            style={{
              height: '100%',
              width: `${parseInt (this.fixedWidth.name, 10) + 33}px`,
              borderRight: '1px solid #e8e8e8',
            }}
          />
        </div>
      );
    }
    return false;
  };

  hideGraphContainer = () => {
    this.setState ({
      showGraphContainer: 'hide',
    });
  };

  render () {
    const {
      loading,
      data = {},
      graphDependencies,
      graphNodesFetchLoading,
    } = this.props;
    const tableFullWidth =
      document.body.clientWidth -
      (document.getElementsByClassName ('ant-layout-sider')[0]
        ? document
            .getElementsByClassName ('ant-layout-sider')[0]
            .style.width.replace ('px', '')
        : 0) -
      48 -
      64;
    this.columns = this.columns.map (item => {
      if (item.dataIndex === 'executeRate') {
        // 最后一列 宽度取rest
        delete this.fixedWidth.executeRate;
        item.width =
          tableFullWidth -
          Object.values (this.fixedWidth).reduce (
            (pre, value = 0) => pre + value,
            0
          );
        this.fixedWidth[item.dataIndex] = `${item.width - 32}`;
      } else {
        item.width = Math.floor (item.percent / 100 * tableFullWidth);
        this.fixedWidth[item.dataIndex] = `${item.width - 32}`;
      }
      return item;
    });
    const {name = 0} = this.fixedWidth;
    const maskWidthNumber = tableFullWidth - name - 36;
    const maskWidth = `${maskWidthNumber}px`;
    this.graphContainerRight.hide = `-${maskWidthNumber + 33}px`;
    return (
      <PageHeaderWrapper>
        <Card bordered={false} onClick={this.hideGraphContainer}>
          <div className={styles.fixedHeightTableList}>
            <div
              onClick={e => e.stopPropagation ()}
              style={{
                right: `${this.graphContainerRight[this.state.showGraphContainer]}`,
                width: maskWidth,
              }}
              className={styles.graphContainer}
            >
              <GraphFlowCustomed
                loading={graphNodesFetchLoading}
                graphDependencies={graphDependencies}
                nodeId={this.state.jobId}
                payloadKey="jobPlanId"
              />
            </div>
            <div className="CommonTableList-Form">{this.renderForm ()}</div>
            <GeneralTable
              rowKey="id"
              bordered
              footer={this.autoFooter}
              scroll={this.scroll}
              loading={loading}
              data={data}
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
