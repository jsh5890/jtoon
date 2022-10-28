import { createRouter, createWebHistory } from "vue-router";
import KmanaView from "../views/KmanaView.vue";
import NdetailView from "../views/lists/NdetailView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: () => import("../views/HomeView.vue"),
    },
    {
      path: "/naver",
      name: "naver",
      component: () => import("../views/NmanaView.vue"),
    },
    {
      path: "/kakao",
      name: "kakao",
      component: KmanaView,
    },
    {
      path: "/naver/list/:href",
      name: "nDetail",
      component: NdetailView,
      props: true,
    },

  ],
});

export default router;
