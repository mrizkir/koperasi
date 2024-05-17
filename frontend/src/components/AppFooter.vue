<template>
  <v-footer app padless fixed dark>
    <v-card class="flex" flat tile>
      <v-divider></v-divider>
      <v-card-text class="py-2 white--text text-center">
        <strong>
          {{ $store.getters["uifront/getNamaAPP"] }} (2024)
        </strong>
        dikembangkan oleh TIM IT Koperasi.
        <v-btn dark icon href="https://github.com/mrizkir/koperasi">
          <v-icon>mdi-github</v-icon>
        </v-btn>
      </v-card-text>
    </v-card>
  </v-footer>
</template>