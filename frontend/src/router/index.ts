import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: "/",
      name: "FrontDashboard",
      meta: {
        title: "DASHBOARD SIMONEV",
      },
      component: () => import("../pages/front/DashboardFront.vue"),
    },
  ]
})

export default router
