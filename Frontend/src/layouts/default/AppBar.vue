<template>
  <v-app-bar flat>
    <v-btn @click="handleClickList">목록</v-btn>
    <v-btn @click="handleClickLogin">로그인</v-btn>
    <v-btn @click="handleClickLogout">로그아웃</v-btn>
  </v-app-bar>
</template>

<script setup>
import {ref} from "vue";
import useMemberInfo from "@/store/useMemberInfo";
import {useRouter} from "vue-router";

const memberStore = useMemberInfo();
const router = useRouter();
const handleClickList = () =>{
  router.push({name:'TodoListPage'})
}

const handleClickLogin = () =>{
  router.push({name:'LoginPage'})
}

const handleClickLogout = ()=>{
  memberStore.logout()
}

</script>
