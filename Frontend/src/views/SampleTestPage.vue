<template>
  <h2>Sample Test Page</h2>
  <v-btn @click="handleClickLogin">Login</v-btn>
  <v-btn @click="handleClickEx1">Ex1</v-btn>
  <v-btn @click="handleClickEx2">Ex2</v-btn>
  <v-btn @click="handleClickEx3">Ex3</v-btn>
</template>

<script setup>

import axios from "axios";
import useMemberInfo from "@/store/useMemberInfo";
import authAxios from "@/util/authAxios";

const handleClickLogin = async () => {
  const res = await axios.post(`http://localhost:8080/api/generate`)

  //{access:xxxx, refresh:xxxx}
  const data = res.data

  const {saveInfo} = useMemberInfo()

  saveInfo(data.access, data.refresh)
}

const handleClickEx1 = async () => {

  const res = await authAxios.get('http://localhost:8080/api/sample/ex1')
  const data = res.data

  console.log(data)
}

const handleClickEx2 = async () => {

  const res = await authAxios.get('http://localhost:8080/api/sample/ex2')
  const data = res.data

  console.log(data)
}

const handleClickEx3 = async () => {

  const res = await authAxios.get('http://localhost:8080/api/sample/ex3')
  const data = res.data

  console.log(data)
}


</script>

<style scoped>

</style>
