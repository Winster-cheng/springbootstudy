import React, {PureComponent} from 'react';
import {connect} from 'dva';
import {Row, Col, Button, Form, Input, Card, Tooltip, Select, DatePicker, Icon} from 'antd';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import GeneralTable from '@/components/GeneralTable';
import GraphFlow from '@/components/GraphFlow4Instance';
import TaskLog from './TaskLog';
import styles from './TaskInstance.less';
import success from "../../assets/icon_status_success.svg";
import fail from "../../assets/icon_status_fail.svg";
// import freeze from "../../assets/icon_status_freeze.svg";
import running from "../../assets/icon_status_running.svg";
import stop from "../../assets/icon_status_stop.svg";
import waitting from "../../assets/icon_status_waitting.svg";

const {Item} = Form;
const {Option} = Select;
@connect (({task, loading}) => ({
  data: task.data,
  allStatus: task.allStatus,
  logs: task.logs,
  loading: loading.effects["task/fetchInstances"],
  graphDependencies: task.instanceNodes,
  graphNodesFetchLoading: loading.effects["task/fetchAllInstanceNodes"],
}))
@Form.create ()
class TaskInstance extends PureComponent {
  statuslogo = {
    1: stop,
    2: waitting,
    3: running,
    4: success,
    5: fail
  }

  statusColor = {
    1: "#8E8E8E",
    2: "#8E8E8E",
    3: "#016FFF",
    4: "#56C61F",
    5: "#E81B26"
  }
  
  state = {
    formValues: {},
    pagination: {
      pageNo: 1,
      date: "",
      bussinessTime: "",
      startTime: "",
      executeTime: "",
    },
    showGraphContainer: "hide",
    taskId: 0,
    expandLevel: 0,// 0 最小化 1 一半 2 最大化
  };

  height4Expand = {
    0:["calc(100% - 32px)","32px"],
    1:["60%","40%"],
    2:["0","100%"],
  }

  graphContainerRight = {
    show: "33px",
    hide: ""
  }

  scroll = {x: false};

  fixedWidth = {
    name: '',
    status: '',
    owner: '',
    type: '',
    bussinessTime: '',
    startTime: '',
    executeTime: '',
  };

  columns = [
    {
      title: '任务名称',
      dataIndex: 'name',
      percent: 20,
      render: (text, {id}) => this.renderTooltip (text, 'name', id),
    },
    {
      title: '状态',
      dataIndex: 'status',
      filters: [],
      percent: 13,
      render: status => (
        <span style={{color: this.statusColor[status.id]}}>
          <Icon component={this.statuslogo[status.id]} style={{marginRight: "8px"}} />
          {status.chineseName}
        </span>),
    },
    {
      title: '数据类型',
      dataIndex: 'type',
      percent: 13,
      render: text => this.renderTooltip (text, 'type'),
    },
    {
      title: '责任人',
      dataIndex: 'owner',
      percent: 10,
      render: text => this.renderTooltip (text, 'owner'),
    },
    {
      title: '业务日期',
      dataIndex: 'bussinessTime',
      percent: 14,
      sorter: true,
      render: text => this.renderTooltip (text, 'bussinessTime'),
    },
    {
      title: '开始时间',
      dataIndex: 'startTime',
      percent: 14,
      sorter: true,
      render: text => this.renderTooltip (text, 'startTime'),
    },
    {
      title: '定时时间',
      dataIndex: 'executeTime',
      sorter: true,
      // percent: 15,
      render: text => this.renderTooltip (text, 'executeTime'),
    },
  ];

  componentDidMount () {
    const {dispatch} = this.props;
    dispatch ({
      type: 'task/fetchInstances'
    });
    dispatch ({
      type: 'task/fetchAllStatus'
    });
  }

  renderTooltip = (text, dataIndex, taskId) => (
    <Tooltip title={text}>
      {taskId
        ? <a
          href="javascript:;"
          style={{maxWidth: `${this.fixedWidth[dataIndex]}px`}}
          onClick={e => this.showGraph (e,taskId)}
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

  showGraph = (e,taskId) => {
    e.stopPropagation();
    this.setState({
      showGraphContainer: "show",
      taskId,
      expandLevel: 0
    })
    this.props.dispatch({
      type: 'task/fetchAllInstanceNodes',
      payload: taskId
    })
    this.props.dispatch({
      type: 'task/fetchLogs',
      payload: taskId
    })
  };

  handleSearch = e => {
    e.preventDefault ();
    const {dispatch, form} = this.props;
    form.validateFields ((err, fieldsValue) => {
      if (err) return;
      fieldsValue = {
        ...fieldsValue,
        date: fieldsValue.date ? fieldsValue.date.format('YYYY-MM-DD') : ""
      }
      this.setState ({
        formValues: fieldsValue,
      });
      console.log(fieldsValue)
      dispatch ({
        type: 'task/fetchInstances',
        payload: {
          ...this.state.pagination,
          ...fieldsValue
        },
      });
    });
  };

  handleFormReset = () => {
    const { form, dispatch } = this.props;
    form.resetFields();
    this.setState({
      formValues: {},
    });
    dispatch({
      type: 'task/fetchInstances'
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
              {getFieldDecorator ('filename') (
                <Input placeholder="请输入" />
              )}
            </Item>
          </Col>
          {/* <Col md={6} sm={24}>
            <Item label="任务状态">
              {getFieldDecorator ('status') (
                <Select
                  style={{ width: "200px"}}
                  showSearch
                  placeholder="请选择"
                  allowClear
                >
                  {this.props.allStatus.map(status => <Option key={status.id} value={status.id}>{status.chineseName}</Option>)}
                </Select>
              )}
            </Item>
          </Col> */}
          <Col md={6} sm={24}>
            <Item label="执行时间">
              {getFieldDecorator ('date') (
                <DatePicker />
              )}
            </Item>
          </Col>
          <Col md={6} sm={24}>
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
    const param = {
      sortName: "",
      sortType: 0,
      ...filters
    }
    if(sorter.columnKey){
      param.sortName = sorter.columnKey;
      if(sorter.order === "descend"){
        param.sortType = 2;
      }else{
        param.sortType = 1;
      }
    }
    param.pageNo = pagination.current;
    this.setState({
      pagination: param
    })
    this.props.dispatch ({
      type: 'task/fetchInstances',
      payload: {
        ...param,
        ...this.state.formValues
      },
    });
  };

  autoFooter = () => {
    const { data = {} } = this.props;
    const { list = [] } = data;
    if(list.length > 0 && list.length < 11){
      const autoFooterHeight = 59 * (10 - list.length);
      return (
        <div style={{height: `${autoFooterHeight}px`}}>
          <div style={{height: "100%",width: `${parseInt(this.fixedWidth.name,10) + 32 + parseInt(this.fixedWidth.status,10) + 33}px`,borderRight: "1px solid #e8e8e8"}} />
        </div>
        )
    }
      return false;
  }

  hideGraphContainer = () => {
    this.setState({
      showGraphContainer: "hide"
    })
  }

  expandLog = (isExpand) => {
    const { expandLevel } = this.state;
    if(isExpand){
      this.setState({
        expandLevel: expandLevel+1
      })
    }else{
      this.setState({
        expandLevel: expandLevel-1
      })
    }

  }

  render () {
    const {loading, data = {}, graphDependencies, graphNodesFetchLoading, allStatus, logs} = this.props;
    const tableFullWidth =
      document.body.clientWidth -
      (document.getElementsByClassName ('ant-layout-sider')[0]
        ? document
            .getElementsByClassName ('ant-layout-sider')[0]
            .style.width.replace ('px', '')
        : 0) - 48 - 64;
    this.columns = this.columns.map (item => {
      if(item.dataIndex === "executeTime"){// 最后一列 宽度取rest
        delete this.fixedWidth.executeTime;
        item.width = tableFullWidth - Object.values(this.fixedWidth).reduce((pre,value=0) => pre + value,0);
        this.fixedWidth[item.dataIndex] = `${item.width - 32}`;
      }else{
        item.width = Math.floor(item.percent / 100 * tableFullWidth);
        this.fixedWidth[item.dataIndex] = `${item.width - 32}`;
      }
      if(item.dataIndex === "status" && item.filters.length === 0){
        item.filters = allStatus.map(status => ({
          text: status.chineseName,
          value: status.id
        }))
      }
      return item;
    });
    const { name = 0, status = 0 } = this.fixedWidth;
    const maskWidthNumber = tableFullWidth - name - status - 36 - 32;
    const maskWidth = `${maskWidthNumber}px`;
    this.graphContainerRight.hide = `-${maskWidthNumber + 33}px`
    return (
      <PageHeaderWrapper>
        <Card bordered={false} onClick={this.hideGraphContainer}>
          <div className={styles.fixedHeightTableList}>
            <div onClick={e => e.stopPropagation()} style={{right:`${this.graphContainerRight[this.state.showGraphContainer]}`,width: maskWidth}} className={styles.graphContainer}>
              <div style={{height: this.height4Expand[this.state.expandLevel][0], width: "100%"}}>
                <GraphFlow 
                  loading={graphNodesFetchLoading} 
                  graphDependencies={graphDependencies} 
                  taskInstanceId={this.state.taskId}
                />
              </div>
              <div style={{height: this.height4Expand[this.state.expandLevel][1], width: "100%"}}>
                <TaskLog logs={logs} expandLog={this.expandLog} expandLevel={this.state.expandLevel} />
              </div>
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
export default TaskInstance;
