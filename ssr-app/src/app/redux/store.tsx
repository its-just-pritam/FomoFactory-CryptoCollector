import { configureStore } from "@reduxjs/toolkit";
import coinStateReducer from "./slice";

export const store = configureStore({
    reducer: {
        coinState: coinStateReducer
    }
})