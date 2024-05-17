import { createRouter, createWebHistory } from 'vue-router/auto'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "FrontDashboard",
      meta: {
        title: "WELCOME",
      },
      component: () => import("../pages/front/DashboardFront.vue"),
    },
    {
      path: "/login",
      name: "FrontLogin",
      meta: {
        title: "LOGIN",
      },
      component: () => import("../pages/front/LoginFront.vue"),
    },
  ]
})

export default router
