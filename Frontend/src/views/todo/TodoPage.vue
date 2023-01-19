<template>
  <h1>Todo Page</h1>
  <h2>{{ userInfo.title }}</h2>
  <h2>{{ userInfo.writer }}</h2>
</template>

<script setup>

import {useRoute, useRouter} from "vue-router";
import authAxios from "@/util/authAxios";
import {ref} from "vue";

const route = useRoute()
const router = useRouter();
const userInfo = ref({title: null, writer: null})

const result = authAxios.get('http://localhost:8080/api/todos/' + route.params.id).then((res) => {
  userInfo.value = res.data
  console.log('[result]')
  console.log(res)
})

// const res = await authAxios.get('http://localhost:8080/api/todos/' + route.params.id)
// userInfo.value = res.data

</script>

<style scoped>

</style>
