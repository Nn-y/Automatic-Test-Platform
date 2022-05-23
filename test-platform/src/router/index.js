import { createRouter, createWebHistory } from 'vue-router'
import Head from "@/components/Head";
import  FunctionalTestCases from '../components/TestTrack/FunctionalTestCases.vue'
import InterfaceTestCases from '../components/TestTrack/InterfaceTestCases.vue'
import PerformanceTestCases from '../components/TestTrack/PerformanceTestCases.vue'
import TestTrackMenu from '../components/TestTrack/TestTrackMenu.vue'
import FunctionTest from '../components/FunctionalTest/FunctionTest.vue'
import PerformanceTest from '../components/PerformanceTest/PerformanceTest.vue'
import InterfaceTest from '../components/InterfaceTest/InterfaceTest.vue'
import Login from "@/components/Login";
import Register from "@/components/Register";

const routes = [
  {
    path: '/',
    component: Login,
    meta:{
      requiresAuth: false, // false表示不需要登录
    }
  },
  {
    path:'/Register',
    component: Register,
    meta:{
      requiresAuth: false, // false表示不需要登录
    }
  },
  {
    path: '/Test',
    redirect: '/TestTrack/function',
    component: Head,
    meta:{
      requiresAuth: true,
    },
    children: [
      {
        path: '/TestTrack',
        redirect: '/TestTrack/function',
        component: TestTrackMenu,
        meta: {name: "testTrack",
          requiresAuth: true, },

        children: [

          {
            path: '/TestTrack/function',
            component: FunctionalTestCases,
            name: 'function',
            meta:{
              requiresAuth: true,
            },

          },
          {
            path: '/TestTrack/interface',
            component: InterfaceTestCases,
            name: 'interface',
            meta:{
              requiresAuth: true,
            },
          },
          {
            path: '/TestTrack/performance',
            component: PerformanceTestCases,
            name: 'performance',
            meta:{
              requiresAuth: true,
            },
          }

        ]
      },
      {
        path: '/functionTest',
        component: FunctionTest,
        name: 'functionTest',
        meta:{
          requiresAuth: true,
        },
      },
      {
        path: '/performanceTest',
        component: PerformanceTest,
        name: 'performanceTest',
        meta:{
          requiresAuth: true,
        },
      },
      {
        path: '/interfaceTest',
        component: InterfaceTest,
        name: 'interfaceTest',
        meta: {name: "interfaceTest",
          requiresAuth: true,},
      }

    ]

  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 路由拦截，判断是否需要登录

router.beforeEach((to, from, next) => {

  // 通过requiresAuth判断当前路由导航是否需要登录

  if (to.matched.some(record => record.meta.requiresAuth)) {

    let token = sessionStorage.getItem('token')

    console.log("token",token)

    // 若需要登录访问，检查是否为登录状态

    if (!token) {

      next({

        path: '/',

        query: { redirect: to.fullPath }

      })

    } else {

      next()

    }

  } else {

    next() // 确保一定要调用 next()

  }

})

export default router
