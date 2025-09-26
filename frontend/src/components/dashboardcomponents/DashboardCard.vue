<script setup lang="ts">
import router from '@/router';

const props = defineProps<{
  title: string;
  imageSrc: string;
  link: string;
}>();

const onCardClicked = () => {
  const resolvedRoute = router.resolve(props.link);
  if (resolvedRoute.matched.length > 0) {
    router.push(props.link);
  } else {
    alert(`Route ${props.link} existiert nicht!`);
  }
};
</script>

<template>
  <v-card @click="onCardClicked" width="500" :image="imageSrc" :title="title" height="300" class="ma-4" />
</template>

<style scoped>
.v-card {
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  color: black;

  &:hover {
    box-shadow: 2px 2px 4px rgb(9, 149, 206);
    transform: translateY(-2px);
    transition: all 0.3s ease;
  }
}
</style>
