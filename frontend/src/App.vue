<script setup lang="ts">
import './style/style.css';
import './style/headerbar.css';
import HeaderBar from "@/components/HeaderBar.vue";

</script>

<template>
  <v-app>
    <v-main>
      <v-container>
        <div class="app-container">
          <div class="header-bar-wrapper">
            <HeaderBar />
          </div>

          <router-view/>
        </div>
      </v-container>
    </v-main>
  </v-app>
</template>

<style></style>
