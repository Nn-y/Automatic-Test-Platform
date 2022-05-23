// 这个就是自己写的 ACE 组件
import CodeEdit from './CodeEditor'

export default {
    install: function (Vue, options) {
        // 引号中的字符串就是之后的组件标签名，即 <ace></ace>
        Vue.component('ace', CodeEdit)
    }
}