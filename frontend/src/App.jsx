import { useEffect, useState } from 'react'
import axios from 'axios'

function App() {

  const [products, setProducts] = useState([])

  async function loadProducts() {

    try {

      const response = await axios.get(
        'http://localhost:8081/api/products'
      )

      setProducts(response.data)

    } catch (error) {

      console.log(error)
    }
  }

  useEffect(() => {

    loadProducts()

  }, [])

  return (

    <div className="container mt-5">

      <h1 className="mb-4">
        Sistema de Produtos
      </h1>

      <div className="card p-4 shadow">

        <table className="table">

          <thead>

          <tr>

            <th>Nome</th>
            <th>Preço</th>
            <th>Descrição</th>

          </tr>

          </thead>

          <tbody>

          {
            products.map(product => (

              <tr key={product.id}>

                <td>{product.name}</td>

                <td>
                  R$ {product.price}
                </td>

                <td>{product.description}</td>

              </tr>
            ))
          }

          </tbody>

        </table>

      </div>

    </div>
  )
}

export default App