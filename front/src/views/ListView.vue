<script setup lang="ts">

</script>

<template>
  <div class="flex-column">
    <el-card v-for="o in 4" :key="o" class="box-card mt-3 justify-content-between">
      <template #header>
        <div class="card-header">
          <span>{{ o + '번 정답' }}</span>
        </div>
      </template>
      <div class="text item">내용</div>
      <div>
        <el-tag>웹</el-tag>
        <el-tag type="success">데이터베이스</el-tag>
        <el-tag type="info">자바</el-tag>
      </div>
    </el-card>
  </div>
</template>

<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

</style>