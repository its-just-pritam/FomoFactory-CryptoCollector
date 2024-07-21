import { createSlice, PayloadAction } from "@reduxjs/toolkit"

interface IPrice {
    usd: number,
    usdMarketCap: number,
    usd24hVol: number,
    usd24hChange: number,
    lastUpdatedAt: string
}

interface ICoinState {
    coin: string;
    prices: IPrice[];
}

const initialState: ICoinState = {
    coin: "bitcoin",
    prices: []
};

const slice = createSlice({
    name: "coin",
    initialState,
    reducers: {
        storeCoin: (state: ICoinState, action:PayloadAction<string>) => {
            state.coin = action.payload
            // console.log("Store coin to redux: ", state.coin)
        },
        storePrices: (state: ICoinState, action:PayloadAction<IPrice[]>) => {
            state.prices = action.payload
            // console.log("Store prices to redux: ", state.prices)
        }
    }
});

export const { storeCoin, storePrices } = slice.actions;
export default slice.reducer;