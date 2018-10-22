export default [
  {
    path: '/',
    component: '../layouts/BasicLayout',
    Routes: ['src/pages/Authorized'],
    authority: ['admin', 'user'],
    routes: [
      {
        path: '/',
        redirect: '/taskSubmit',
      },
      {
        path: '/taskSubmit',
        name: 'taskSubmit',
        icon: "icon_task_submit",
        component: './Task/TaskSubmit',
      },
      {
        path: '/jobPlan',
        name: 'jobPlan',
        icon: "icon_job_plan",
        component: './Job/jobPlan',
      },
      {
        path: '/taskInstance',
        name: 'taskInstance',
        icon: "icon_task_instance",
        component: './Task/taskInstance',
      },
    ],
  },
];
