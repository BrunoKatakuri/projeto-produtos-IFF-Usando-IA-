import { useEffect, useState } from 'react'

import api from '../services/api'

import ProductForm from '../components/ProductForm'
import ProductList from '../components/ProductList'
import { ToastContainer } from 'react-toastify'

function Home() {

    const [products, setProducts] = useState([])

    const [editingProduct, setEditingProduct] = useState(null)

    async function loadProducts() {

        try {

            const response = await api.get('/products')

            setProducts(response.data)

        } catch (error) {

            console.log(error)
        }
    }

    function handleEditProduct(product) {

        setEditingProduct(product)
    }

    function clearEditing() {

        setEditingProduct(null)
    }

    useEffect(() => {

        loadProducts()

    }, [])

    return  ( 

        <div className="container-fluid container-md mt-5">

            <h1 className="mb-4">
                Sistema de Produtos
            </h1>

            <ProductForm
                onProductCreated={loadProducts}
                editingProduct={editingProduct}
                clearEditing={clearEditing}
            />

            <ProductList
                products={products}
                onProductDeleted={loadProducts}
                onEditProduct={handleEditProduct}
            />
        <ToastContainer />
        </div>
        
    )
}

export default Home