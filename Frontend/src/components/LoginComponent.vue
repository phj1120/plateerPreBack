<template>
  <div>
    <h2>Login Component </h2>
    <v-text-field v-model="memberInfo.username"></v-text-field>
    <v-text-field v-model="memberInfo.password"></v-text-field>

    <v-btn @click="handleClickLogin(memberInfo)">Login</v-btn>
  </div>
</template>

<script setup>

import axios from "axios";
import useMemberInfo from "@/store/useMemberInfo";
import {ref} from "vue";

const memberInfo = ref({username: null, password: null})

const emits = defineEmits(['clickLogin'])
const handleClickLogin = async (memberInfo) => {
  const res = await axios.post(`http://localhost:8080/api/login`, memberInfo)

  //{access:xxxx, refresh:xxxx}
  const data = res.data

  const {saveInfo} = useMemberInfo()

  saveInfo(data.access, data.refresh)

  emits('clickLogin',[])
}
</script>

<style scoped>

</style>
