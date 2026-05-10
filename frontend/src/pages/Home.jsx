import { useEffect, useState } from 'react'

import api from '../services/api'

import ProductForm from '../components/ProductForm'
import ProductList from '../components/ProductList'

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

    return (

        <div className="container mt-5">

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

        </div>
    )
}

export default Home