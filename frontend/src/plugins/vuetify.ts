import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";

export default createVuetify({
  icons: {
    defaultSet: 'fa',
    sets: {
      fa: {
        //eslint-disable-next-line @typescript-eslint/no-explicit-any
        component: FontAwesomeIcon as any,
      }
    },
  },
  theme: {
    themes: {
      light: {
        colors: {
          primary: '#0995ce',
        }
      },
    },
    defaultTheme: 'light',
  },
})

