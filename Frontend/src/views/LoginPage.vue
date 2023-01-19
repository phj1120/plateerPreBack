<template>
    <h1>Login Page </h1>
    <LoginComponent @clickLogin="clickLogin"></LoginComponent>
</template>

<script setup>
import LoginComponent from "@/components/LoginComponent";
import {useRouter} from "vue-router";

const router = useRouter();

const clickLogin = () => {
  router.push({name: 'TodoListPage'})
}


</script>

<style scoped>

</style>
