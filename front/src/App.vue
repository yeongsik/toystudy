<script setup lang="ts">
import {RouterLink, RouterView} from 'vue-router'
import {ref} from 'vue'

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>
<template>
  <header>
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        :ellipsis="false"
        @select="handleSelect"
    >
      <el-menu-item index="0">
        <RouterLink to="/">Home</RouterLink>
      </el-menu-item>
      <div class="flex-grow"/>
      <el-sub-menu index="1">
        <template #title>퀴즈</template>
        <el-menu-item index="2-1">
          <RouterLink to="/list">퀴즈 목록</RouterLink>
        </el-menu-item>
        <el-menu-item index="2-2">
          <RouterLink to="/write">퀴즈 작성</RouterLink>
        </el-menu-item>
        <el-menu-item index="2-3">
          <RouterLink to="/select">퀴즈 조회</RouterLink>
        </el-menu-item>
      </el-sub-menu>
      <el-menu-item index="2">
        <RouterLink to="/list">회원가입</RouterLink>
      </el-menu-item>
      <el-menu-item index="3">
        <RouterLink to="/list">로그인</RouterLink>
      </el-menu-item>
    </el-menu>
  </header>
  <router-view></router-view>
</template>

<style>
.flex-grow {
  flex-grow: 1;
}

a {
  text-decoration: none;
}
</style>