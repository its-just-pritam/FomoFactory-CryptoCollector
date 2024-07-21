import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import { CustomProvider } from "./redux/provider";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Crypto Collector",
  description: "Built for FomoFactory",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={inter.className}>
        <CustomProvider>
          {children}
        </CustomProvider>
      </body>
    </html>
  );
}
