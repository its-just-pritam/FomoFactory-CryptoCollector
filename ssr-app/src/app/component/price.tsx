"use client";
import { useEffect, useState } from "react";
import ActivateCoin from "./modal";
import { storePrices } from "@/app/redux/slice";
import { useDispatch, useSelector } from "react-redux";
import classNames from "classnames";

const fetchData = async (dispatch:any, currentCoin:any) => {
    const url = '/api/poller?name=' + currentCoin;
    await fetch(url, {
        method: 'GET'
    }).then(async (response) => {
        if (response.ok) {
            const result = await response.json();
            dispatch(storePrices(result));
        } else throw new Error('Network response was not ok');
    }).catch((err) => {
        console.log("Error Occurred: ", err.message);
    });
};

export default function Price() {

    const currentCoin = useSelector((data: any) => data.coinState.coin);
    const currentPrices = useSelector((data: any) => data.coinState.prices);
    const [openModal, setopenModal] = useState(false);
    const dispatch = useDispatch();

    interface TableProps {
        data: { 
            usd: number;
            usdMarketCap: number;
            usd24hVol: number;
            usd24hChange: number;
            lastUpdatedAt: number;
        }[];
    }
    
    const Table: React.FC<TableProps> = ({ data }) => {
        const columns = [
            'usd',
            'usdMarketCap',
            'usd24hVol',
            'usd24hChange',
            'lastUpdatedAt'
        ];
    
        return (
            <div className="overflow-x-auto mb-20">
                <table className="min-w-full bg-white border border-gray-200">
                    <thead>
                        <tr>
                            {columns.map((column, index) => (
                                <th
                                    key={index}
                                    className="py-2 px-4 border-b border-gray-200 bg-gray-50 text-left text-gray-600 font-semibold uppercase tracking-wider"
                                >
                                    {column.replace(/([a-z])([A-Z])/g, '$1 $2')}
                                </th>
                            ))}
                        </tr>
                    </thead>
                    <tbody>
                        {data.map((row, rowIndex) => (
                            <tr key={rowIndex} className="bg-gray-900">
                                {columns.map((column, colIndex) => (
                                    <td
                                        key={colIndex}
                                        className="py-2 px-4 border-b border-gray-200 text-gray-100"
                                    >
                                        {row[column as keyof typeof row]}
                                    </td>
                                ))}
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        );
    };

    useEffect(() => {
        const intervalId = setInterval(() => {
            console.log('Logs every 30 seconds');
            fetchData(dispatch,currentCoin);
        }, 30000);
        return () => clearInterval(intervalId);
      }, [currentCoin]);

      useEffect(() => {
        fetchData(dispatch,currentCoin);
      }, [currentCoin]);

    return (
        <div className={classNames("container mx-auto p-4", openModal ? "blur-sm" : "")}>
            <div className="mb-5 flex items-center justify-center gap-x-6 lg:justify-start">
                <h1 className="text-3xl font-bold mr-5">
                    {currentCoin.toUpperCase()}
                </h1>
                <a className="focus:outline-none rounded-md transition bg-green-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-green-500 hover:cursor-pointer focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2" 
                onClick={() => setopenModal(true)}>
                Change Coin
                </a>
            </div>
            <ActivateCoin open={openModal} setOpen={setopenModal}/>
            <Table data={currentPrices} />
            <div className="mt-10">
                Built by Pritam Mallick<br/>
                <a target="blank" className="text-green-400 hover:text-green-200 font-mono" href='https://www.linkedin.com/in/pritammallick20/'>LinkedIn</a><br/>
                <a target="blank" className="text-green-400 hover:text-green-200 font-mono" href='https://github.com/its-just-pritam'>GitHub</a>
            </div>
        </div>
    );
}