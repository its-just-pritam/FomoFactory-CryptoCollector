import { MongoClient } from "mongodb";

const MONGODB_URI = "mongodb://ffuser:ffpassword@ff-mongo:27017/ffcryptodb";

export default async function handler(req: any, res: any) {
    
    if (req.method === "GET") {

        console.log(req.query)
        const { name } = req.query;
        console.log("Fetching prices for coin: ", name);
        const client = new MongoClient(MONGODB_URI);
        try {
            await client.connect();
            const database = client.db("ffcryptodb");
            const collection = database.collection("CryptoCurrency");
            const allData = await collection
            .find({ name: name }) // Filter by name
            .sort({ lastUpdatedAt: -1 }) // Sort by lastUpdatedAt in descending order
            .limit(20) // Limit to top 20 results
            .toArray();

            const filteredData = allData.map(({ _id, name, _class, ...rest }) => ({
                lastUpdatedAt: new Date(rest.lastUpdatedAt * 1000).toISOString(),
                usd: rest.usd,
                usdMarketCap: rest.usdMarketCap,
                usd24hVol: rest.usd24hVol,
                usd24hChange: rest.usd24hChange,
            }));

            res.status(200).json(filteredData);
        } catch (error) {
            console.log(error)
            res.status(500).json({ message: "Unknown error occurred" });
        } finally {
            await client.close();
        }
    } else {
        res.status(405).json({ message: "Method not allowed!" });
    }
}