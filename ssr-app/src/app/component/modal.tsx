import { Dialog, DialogPanel, DialogTitle, Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/react'
import { CurrencyDollarIcon } from '@heroicons/react/24/outline'
import { useDispatch, useSelector } from 'react-redux';
import { storeCoin } from '../redux/slice';

const listOfCoins = [
    "bitcoin",
    "dogecoin",
    "ethereum",
    "pepe",
    "tron"
]

function CoinRadio({coin, dispatch, setOpen}: any) {

    return (
      <Menu as="div" className="mt-2 relative inline-block text-left">
        <div className="mt-4">
            {listOfCoins.map((item, index) => (
                <div key={index}>
                <input
                    id={item}
                    name={item}
                    type="radio"
                    value={item}
                    checked={coin === item}
                    onChange={() => {
                        dispatch(storeCoin(item));
                        setOpen(false);
                    }}
                    className="w-4 h-4 border-gray-300 focus:ring-green-900 align-middle"
                />
                <label className="ml-4 text-gray-700">{item.charAt(0).toUpperCase() + item.slice(1)}</label>
                </div>
                
            ))}
          </div>
      </Menu>
    )
  }

export default function ActivateCoin({open, setOpen}: any) {

  const currentCoin = useSelector((data: any) => data.coinState.coin);
  const dispatch = useDispatch();
  return (
    <Dialog open={open} onClose={setOpen} className="relative z-10">
      <div className="fixed inset-0 z-10 w-screen overflow-y-auto">
        <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
          <DialogPanel
            className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all data-[closed]:translate-y-4 data-[closed]:opacity-0 data-[enter]:duration-300 data-[leave]:duration-200 data-[enter]:ease-out data-[leave]:ease-in sm:my-8 sm:w-full sm:max-w-lg data-[closed]:sm:translate-y-0 data-[closed]:sm:scale-95"
          >
            <div className="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
              <div className="sm:flex sm:items-start">
                <div className="mx-auto flex h-12 w-12 flex-shrink-0 items-center justify-center rounded-full bg-green-500 sm:mx-0 sm:h-10 sm:w-10">
                  <CurrencyDollarIcon aria-hidden="true" className="h-6 w-6 text-green-100" />
                </div>
                <div className="mt-3 text-center sm:ml-4 sm:mt-0 sm:text-left">
                  <DialogTitle as="h3" className="font-semibold leading-8 text-gray-900 text-xl">
                    Select Coin
                  </DialogTitle>
                  <CoinRadio coin={currentCoin} dispatch={dispatch} setOpen={setOpen}/>
                </div>
              </div>
            </div>
            <div className="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
              <button
                type="button"
                onClick={() => setOpen(false)}
                className="inline-flex w-full justify-center rounded-md bg-red-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-red-500 sm:ml-3 sm:w-auto"
              >
                Cancel
              </button>
            </div>
          </DialogPanel>
        </div>
      </div>
    </Dialog>
  )
}