<script setup lang="ts">
import router from '@/router'

const props = defineProps<{
  title: string
  imageSrc: string
  link: string
}>()

const onCardClicked = () => {
  const resolvedRoute = router.resolve(props.link)
  if (resolvedRoute.matched.length > 0) {
    router.push(props.link)
  } else {
    alert(`Route ${props.link} existiert nicht!`)
  }
}
</script>

<template>
  <v-card @click="onCardClicked" width="500" height="300" class="ma-4">
    <v-img :src="imageSrc"></v-img>
    <v-card-title class="text-center">{{ title }}</v-card-title>
  </v-card>
</template>

<style scoped>
.v-card {
  cursor: pointer;
  justify-content: center;
  align-content: center;

  &:hover {
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    transform: translateY(-2px);
    transition: all 0.3s ease;
  }
}
</style>
