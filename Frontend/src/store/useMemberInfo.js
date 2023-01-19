import {defineStore} from "pinia";
import {ref} from "vue";


const useMemberInfo = defineStore('useMemberInfo',() => {

  const memberInfo = ref({access:null, refresh:null})

  const saveInfo = (accessToken, refreshToken) => {
    memberInfo.value.access = accessToken
    memberInfo.value.refresh = refreshToken

    //cookie
    localStorage.setItem("access", accessToken)
    localStorage.setItem("refresh", refreshToken)
  }

  const getTokens = () => {
      memberInfo.value.access = localStorage.getItem("access")
      memberInfo.value.refresh = localStorage.getItem("refresh")

    return memberInfo
  }

  const logout = () =>{
    memberInfo.value.access = null
    memberInfo.value.refresh = null

    localStorage.clear()
  }

  return {memberInfo, saveInfo, getTokens, logout}
})

export default useMemberInfo
