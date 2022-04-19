import { createRouter, createWebHistory } from 'vue-router'
import Head from "@/components/Head";
import  FunctionalTestCases from '../components/TestTrack/FunctionalTestCases.vue'
import InterfaceTestCases from '../components/TestTrack/InterfaceTestCases.vue'
import PerformanceTestCases from '../components/TestTrack/PerformanceTestCases.vue'
import TestTrackMenu from '../components/TestTrack/TestTrackMenu.vue'
import FunctionTest from '../components/FunctionalTest/FunctionTest.vue'
import PerformanceTest from '../components/PerformanceTest/PerformanceTest.vue'
import InterfaceTest from '../components/InterfaceTest/InterfaceTest.vue'

const routes = [
  {
    path: '/',
    redirect:'/TestTrack/function',
    component: Head,
    children:[
      {
        path: '/TestTrack',
        redirect:'/TestTrack/function',
        component: TestTrackMenu,
        meta:{ name:"testTrack" },

        children:[

          {
            path:'/TestTrack/function',
            component: FunctionalTestCases,
            name:'function',

          },
          {
            path: '/TestTrack/interface',
            component: InterfaceTestCases,
            name:'interface',

          },
          {
            path: '/TestTrack/performance',
            component: PerformanceTestCases,
            name:'performance',

          }

        ]
      },
      {
        path: '/functionTest',
        component: FunctionTest,
        name:'functionTest'

      },
      {
        path: '/performanceTest',
        component: PerformanceTest,
        name:'performanceTest'

      },
      {
        path:'/interfaceTest',
        component: InterfaceTest,
        name:'interfaceTest',
        meta:{ name:"interfaceTest" },
      }

    ]

  },
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
