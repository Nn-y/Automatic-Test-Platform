import { createStore } from 'vuex'

export default createStore({
  state: {
    project:sessionStorage.getItem('project'),
    user:sessionStorage.getItem('user'),
  },
  getters: {
  },
  mutations: {
    changeProject(state,name){
      state.project = name
      sessionStorage.setItem('project',name)
    },
    showUser(state,name){
      state.user = name
      sessionStorage.setItem('user',name)
    },
  },
  actions: {
    asynChange(context,name){
      context.commit("changeProject",name)
    },
    asynShow(context,name){
      context.commit("showUser",name)
    },

  },
  modules: {
  }
})
