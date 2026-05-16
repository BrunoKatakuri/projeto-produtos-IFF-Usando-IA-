import {
    useEffect,
    useState
} from 'react'

import {
    useParams,
    Link
} from 'react-router-dom'

import api from '../services/api'

function ProductDetails() {

    const { id } = useParams()

    const [product, setProduct] = useState(null)

    async function loadProduct() {

        try {

            const response = await api.get(
                `/products/${id}`
            )

            setProduct(response.data)

        } catch (error) {

            console.log(error)
        }
    }

    useEffect(() => {

        loadProduct()

    }, [])

    if (!product) {

        return (

            <div className="container mt-5">

                <h3>
                    Carregando...
                </h3>

            </div>
        )
    }

    return (

        <div className="container mt-5">

            <div className="card shadow p-4">

                <h1 className="mb-4">
                    {product.name}
                </h1>

                <h4 className="mb-3">
                    R$ {product.price}
                </h4>

                <p>
                    {product.description}
                </p>

                <p>
                    <strong>Categoria:</strong>
                    {product.category}
                </p>

                <p>
                    <strong>Estoque:</strong>
                    {product.stockQuantity}
                </p>

                <p>
                    <strong>Status:</strong>
                    {product.active ? "Ativo" : "Inativo"}
                </p>

                <Link
                    to="/"
                    className="btn btn-primary"
                >
                    Voltar
                </Link>

            </div>

        </div>
    )
}

export default ProductDetails