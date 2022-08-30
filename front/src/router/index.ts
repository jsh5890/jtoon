import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import NmanaView from "../views/NmanaView.vue";
import KmanaView from "../views/KmanaView.vue";
import NdetailView from "../views/lists/NdetailView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "home",
      component: HomeView,
    },
    {
      path: "/naver",
      name: "naver",
      component: NmanaView,
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
