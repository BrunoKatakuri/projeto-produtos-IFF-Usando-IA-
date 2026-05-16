import {
    Routes,
    Route,
    Link
} from 'react-router-dom'


import Home from './pages/Home'
import ProductDetails from './pages/ProductDetails'
import UsersPage from "./pages/users/UsersPage";
import UserDetails from "./pages/users/UserDetails";

function App() {

    return (
        <>
            <nav className="p-3 d-flex gap-2">

                <Link
                    to="/"
                    className="btn btn-primary"
                >
                    Produtos
                </Link>

                <Link
                    to="/users"
                    className="btn btn-dark"
                >
                    Usuários
                </Link>

            </nav>

            <Routes>

                <Route
                    path="/"
                    element={<Home />}
                />

                <Route
                    path="/products/:id"
                    element={<ProductDetails />}
                />

                <Route
                    path="/users"
                    element={<UsersPage />}
                />

                <Route
                    path="/users/:id"
                    element={<UserDetails />}
                />

            </Routes>

        </>
    )
}

export default App