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
        icon: "https://img.maihaoche.com/mhc_fe/ghost/taskSubmitMenuIcon.svg",
        component: './Task/TaskSubmit',
      },
      {
        path: '/jobPlan',
        name: 'jobPlan',
        icon: "https://img.maihaoche.com/mhc_fe/ghost/jobPlanMenuIcon.svg",
        component: './Job/jobPlan',
      },
      {
        path: '/taskInstance',
        name: 'taskInstance',
        icon: "https://img.maihaoche.com/mhc_fe/ghost/taskInstanceMenuIcon.svg",
        component: './Task/taskInstance',
      },
    ],
  },
];
