package com.mhc.bi.controller.taskInstance;

import com.mhc.bi.common.ActionResult;
import com.mhc.bi.common.ActionResult2;
import com.mhc.bi.controller.taskInstance.FormBean.TaskInstanceGetDependency;
import com.mhc.bi.controller.taskInstance.FormBean.TaskInstanceGetLog;
import com.mhc.bi.controller.taskInstance.FormBean.TaskInstanceGetMoreDependencies;
import com.mhc.bi.controller.taskInstance.FormBean.TaskInstanceSelect;
import com.mhc.bi.domain.theadvisor.TaskInstance;
import com.mhc.bi.service.TaskInstanceService;
import com.mhc.bi.vo.PageMessage;
import com.mhc.bi.vo.taskinstance.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baiyan
 * @date 2018/10/30
 * @description
 */
@RestController
@RequestMapping("/taskInstance")
public class TaskInstanceController {
    @Autowired
    TaskInstanceService taskInstanceService;

    ActionResult actionResult;

    @PostMapping("/select")
    public ActionResult select(@RequestBody TaskInstanceSelect taskInstanceSelect) {
        ActionResult actionResult = new ActionResult();
        try {
            int pageSize = taskInstanceSelect.getPageSize();
            int pageNo = taskInstanceSelect.getPageNo();
            String date = taskInstanceSelect.getDate();
            String fileName = taskInstanceSelect.getFileName();
            int[] status = taskInstanceSelect.getStatus();
            String sortName = taskInstanceSelect.getSortName();
            Integer sortType = taskInstanceSelect.getSortType();

            int totalCount = 0;
            int totalPage = 0;
            TaskInstanceView taskInstanceView;
            String sql;
            PageMessage pageMessage = new PageMessage();
            List<TaskInstanceView> taskInstanceViewList = new ArrayList<>();
            List<TaskInstance> taskInstanceList;

            if (date.length() != 0) { //三套条件组合 筛选条件（date/fileName/status）+排序关键字（executeTime/bussinessTime/startTime），其中bussinessTime先默认executeTime-1 +排序顺序（0/1/2）
                totalCount = taskInstanceService.getTotalCountByDate(date);
                sql = "select * from taskinstance where execute_day=\"" + date + "\"" + getSortNameAndLimitSQL(sortName, sortType, pageSize, pageNo);
            } else if (fileName.length() != 0) {
                totalCount = taskInstanceService.getTotalCountByFileName(fileName);
                sql = "select * from taskinstance where name like  \"%" + fileName + "%\"" + getSortNameAndLimitSQL(sortName, sortType, pageSize, pageNo);
            } else if (status.length != 0) {
                //TODO 这里的getTotalCountByStat[]us是直接调用JDBC的，修改成xml配置
                totalCount = taskInstanceService.getTotalCountByStatus(status);
                StringBuffer sb = new StringBuffer();
                sb.append("(");
                int count = 0;
                for (int i : status) {
                    if (count == 0) sb.append(i);
                    else sb.append("," + i);
                    count++;
                }
                sb.append(")");
                sql = "select * from taskinstance where status in " + sb.toString() + getSortNameAndLimitSQL(sortName, sortType, pageSize, pageNo);
            } else {
                totalCount = taskInstanceService.getTotalCount();
                sql = "select * from taskinstance " + getSortNameAndLimitSQL(sortName, sortType, pageSize, pageNo);
            }
            taskInstanceList = taskInstanceService.executeDefineSql(sql);
            totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
            for (TaskInstance taskInstance : taskInstanceList) {
                taskInstanceView = new TaskInstanceView();
                taskInstanceView.initByTaskInstance(taskInstance);
                taskInstanceViewList.add(taskInstanceView);
            }
            pageMessage.setTotalPage(totalPage);
            pageMessage.setPageNo(pageNo);
            pageMessage.setTotalPage(totalPage);
            pageMessage.setTotalCount(totalCount);
            pageMessage.setList(taskInstanceViewList);
            pageMessage.setPageSize(pageSize);
            actionResult.setDataValue(pageMessage);
            actionResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }

    //select方法的orderBy和limit条件
    public String getSortNameAndLimitSQL(String sortName, int sortType, int pageSize, int pageNo) {
        int start = (pageNo - 1) * pageSize;
        String sql;
        if (!sortName.equals("startTime")) sortName = "execute_day"; //目前bussinessTime先替换成executeTime
        else sortName = "start_time";
        if (sortType == 0)
            sql = "";
        else if (sortType == 1) {
            sql = "order by " + sortName + " asc";
        } else sql = "order by " + sortName + " desc";
        return sql + " limit " + start + "," + pageSize;
    }

    //目前的Status先不接入comman包中的status
    @PostMapping("/getStatus")
    public ActionResult getStatus() {
        try {
            List<Status> list = new ArrayList<Status>();
            for (int i = 1; i < 6; i++) {
                list.add(Status.getStatus(i));
            }
            actionResult = new ActionResult();
            actionResult.success();
            actionResult.setList(list);
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/getDependencies")
    public ActionResult getDependencies(@RequestBody TaskInstanceGetDependency taskInstanceGetDependency) {
        ActionResult actionResult = new ActionResult();
        try {
            int centerId = taskInstanceGetDependency.getTaskInstanceId();
            TaskInstanceDependency taskInstanceDependency;
            List<TaskInstanceDependency> taskInstanceDependencies = new ArrayList<TaskInstanceDependency>();
            List<TaskInstance> taskInstanceChildrenList = taskInstanceService.getChildrenListById(centerId);
            List<TaskInstance> taskInstanceParentList = taskInstanceService.getParentListById(centerId);

            //给父节点生成VO类
            for (TaskInstance taskInstance : taskInstanceParentList) {
                boolean hasChildren = false;
                boolean hasParent = false;
                taskInstanceDependency = new TaskInstanceDependency();
                if (taskInstanceService.getChildrenListById(taskInstance.getId()).size() != 0) hasChildren = true;
                if (taskInstanceService.getParentListById(taskInstance.getId()).size() != 0) hasParent = true;
                taskInstanceDependency.initAsParentNode(taskInstance, hasParent, hasChildren, centerId);
                taskInstanceDependencies.add(taskInstanceDependency);
            }
            //给子节点生成VO类
            for (TaskInstance taskInstance : taskInstanceChildrenList) {
                boolean hasChildren = false;
                boolean hasParent = false;
                taskInstanceDependency = new TaskInstanceDependency();
                if (taskInstanceService.getParentListById(taskInstance.getId()).size() != 0) hasParent = true;
                if (taskInstanceService.getChildrenListById(taskInstance.getId()).size() != 0) hasChildren = true;
                taskInstanceDependency.initAsChildNode(taskInstance, hasParent, hasChildren, centerId);
                taskInstanceDependencies.add(taskInstanceDependency);
            }

            //给中心节点生产VO类
            taskInstanceDependency = new TaskInstanceDependency();
            taskInstanceDependency.init(taskInstanceService.selectTaskInstanceById(centerId), taskInstanceService.getParentListById(centerId), taskInstanceService.getChildrenListById(centerId));
            taskInstanceDependencies.add(taskInstanceDependency);
            actionResult.success();
            actionResult.setList(taskInstanceDependencies);
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;
    }

    @PostMapping("/getMoreDependencies")
    public ActionResult2 getMoreDependencies(@RequestBody TaskInstanceGetMoreDependencies taskInstanceGetMoreDependencies) {
        int taskInstanceId = taskInstanceGetMoreDependencies.getTaskInstanceId();
        boolean isTop = taskInstanceGetMoreDependencies.getIsTop();
        ActionResult2 actionResult = new ActionResult2();
        try {
            List<TaskInstance> taskInstanceList;
            List<TaskInstanceExtend> taskInstanceExtends = new ArrayList<>();
            TaskInstanceExtend taskInstanceExtend;
            if (isTop) {
                taskInstanceList = taskInstanceService.getParentListById(taskInstanceId);
                for (TaskInstance taskInstance : taskInstanceList) {
                    taskInstanceExtend = new TaskInstanceExtend();
                    taskInstanceExtend.initAsParent(taskInstance, taskInstanceService.getParentIdByTaskInstance(taskInstance.getId()), taskInstanceService.getChildrenIdByTaskInstance(taskInstance.getId()));
                    taskInstanceExtends.add(taskInstanceExtend);
                }
            } else {
                taskInstanceList = taskInstanceService.getChildrenListById(taskInstanceId);
                for (TaskInstance taskInstance : taskInstanceList) {
                    taskInstanceExtend = new TaskInstanceExtend();
                    taskInstanceExtend.initAsChild(taskInstance, taskInstanceService.getParentIdByTaskInstance(taskInstance.getId()), taskInstanceService.getChildrenIdByTaskInstance(taskInstance.getId()));
                    taskInstanceExtends.add(taskInstanceExtend);
                }
            }
            actionResult.success();
            actionResult.setList(taskInstanceExtends);
            actionResult.setIsTop(isTop);
            actionResult.setDataValue(taskInstanceId);
        } catch (Exception e) {
            e.printStackTrace();
            actionResult.fail();
        }
        return actionResult;

    }

    //TODO 目前日志模块未完成，先返回模拟的数据
    @PostMapping("/getLogs")
    public ActionResult getLogs(TaskInstanceGetLog taskInstanceGetLog) {
        ActionResult actionResult = new ActionResult();
        try {
            List<LogVO> logVoList = new ArrayList<>();
            LogVO logVO1 = new LogVO();
            logVO1.setStatus(Status.getStatus(1));
            logVO1.setContent("模拟日志1：Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@1ad53dee]\n" +
                    "Creating a new SqlSession\n" +
                    "SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7b4dfa22] was not registered for synchronization because synchronization is not active\n" +
                    "JDBC Connection [ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC4Connection@5ec35d94]]] will not be managed by Spring\n" +
                    "==>  Preparing: select * from taskinstance where input like concat('%', ?, '%') ");
            logVO1.setTime("2018 11-01");

            LogVO logVO2 = new LogVO();
            logVO2.setStatus(Status.getStatus(2));
            logVO2.setContent("模拟日志2：Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@2ad53dee]\n" +
                    "Creating a new SqlSession\n" +
                    "SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7b4dfa22] was not registered for synchronization because synchronization is not active\n" +
                    "JDBC Connection [ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC4Connection@5ec35d94]]] will not be managed by Spring\n" +
                    "==>  Preparing: select * from taskinstance where input like concat('%', ?, '%') ");
            logVO2.setTime("2018 11-01");

            LogVO logVO3 = new LogVO();
            logVO3.setStatus(Status.getStatus(3));
            logVO3.setContent("模拟日志3：Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3ad53dee]\n" +
                    "Creating a new SqlSession\n" +
                    "SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7b4dfa33] was not registered for synchronization because synchronization is not active\n" +
                    "JDBC Connection [ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC4Connection@5ec35d94]]] will not be managed by Spring\n" +
                    "==>  Preparing: select * from taskinstance where input like concat('%', ?, '%') ");
            logVO3.setTime("2018 11-03");

            LogVO logVO4 = new LogVO();
            logVO4.setStatus(Status.getStatus(4));
            logVO4.setContent("模拟日志4：Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@4ad54dee]\n" +
                    "Creating a new SqlSession\n" +
                    "SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7b4dfa44] was not registered for synchronization because synchronization is not active\n" +
                    "JDBC Connection [ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC4Connection@5ec45d94]]] will not be managed by Spring\n" +
                    "==>  Preparing: select * from taskinstance where input like concat('%', ?, '%') ");
            logVO4.setTime("2018 11-04");

            LogVO logVO5 = new LogVO();
            logVO5.setStatus(Status.getStatus(5));
            logVO5.setContent("模拟日志5：Closing non transactional SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@5ad55dee]\n" +
                    "Creating a new SqlSession\n" +
                    "SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@7b5dfa55] was not registered for synchronization because synchronization is not active\n" +
                    "JDBC Connection [ProxyConnection[PooledConnection[com.mysql.jdbc.JDBC5Connection@5ec55d95]]] will not be managed by Spring\n" +
                    "==>  Preparing: select * from taskinstance where input like concat('%', ?, '%') ");
            logVO5.setTime("2018 11-05");
            logVoList.add(logVO1);
            logVoList.add(logVO2);
            logVoList.add(logVO3);
            logVoList.add(logVO4);
            logVoList.add(logVO5);
            actionResult.setList(logVoList);
            actionResult.success();
        } catch (Exception e) {
            actionResult.fail();
            e.printStackTrace();
        }
        return actionResult;
    }
}
