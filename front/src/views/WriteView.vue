<script setup lang="ts">
import {ref} from 'vue';
import axios from 'axios';

const name = ref('')
const content = ref('')
const category = ref('')

const write = function() {
  alert(category.value + " " + content.value + " " + name.value);
  console.log(category.value, content.value, name.value);

  axios.post("http://localhost:8080/quizzes", {
    name : name.value,
    content : content.value,
    category : category.value
  });
}
</script>

<template>
  <!--  퀴즈 작성 1. 카테고리 2.문제 3.정답-->
  <div class="mt-4">
    <el-select v-model="category" placeholder="카테고리">
      <el-option label="데이터베이스" value="DB"/>
      <el-option label="웹" value="WEB"/>
      <el-option label="자바" value="JAVA"/>
    </el-select>
  </div>
  <div class="mt-2">
    <el-input type="textarea" rows="10" v-model="content" maxlength="200" show-word-limit placeholder="문제를 입력해주세요">
    </el-input>
  </div>
  <div class="mt-2">
    <el-input v-model="name" placeholder="정답을 입력해주세요">
    </el-input>
  </div>
  <div class="mt-2">
    <el-button type="primary" @click="write()">저장</el-button>
    <el-button type="plain" @click="$router.back()">취소</el-button>
  </div>
</template>

<style>
.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}
</style>