import { createStore } from 'vuex'

export default createStore({
  state: {
    project:''
  },
  getters: {
  },
  mutations: {
    changeProject(state,name){
      state.project = name
    }
  },
  actions: {
    asynChange(context,name){
      context.commit("changeProject",name)
    }
  },
  modules: {
  }
})
