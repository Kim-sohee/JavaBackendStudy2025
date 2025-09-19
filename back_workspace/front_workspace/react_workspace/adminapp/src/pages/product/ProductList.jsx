import { useEffect, useState } from "react"
import { getProducts } from "../../utils/ProductApi";
import { Link } from "react-router-dom";

export default function ProductList(){
    const [productList, setProductList] = useState([]);

    //서버에 요청하기
    const getProductList = async()=>{
        console.log("상품 목록 요청할거임");
        const response = await getProducts(); //response.data가 json임
        console.log("서버에서 받아온 결과 response ", response);
        setProductList(response.data.data);
    }

    useEffect(()=>{
        getProductList();
    },[]);  //의존성 배열이 비워져 있으면, 페이지가 처음 렌더링될 때, 1회만 호출

    return (
        <div className="row">
          <div className="col-12">
            <div className="card">
              <div className="card-header">
                <h3 className="card-title">상품목록</h3>

                <div className="card-tools">
                  <div className="input-group input-group-sm" style={{width: '150px'}}>
                    <input type="text" name="table_search" className="form-control float-right" placeholder="Search"/>

                    <div className="input-group-append">
                      <button type="submit" className="btn btn-default">
                        <i className="fas fa-search"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              {/* <!-- /.card-header --> */}
              <div className="card-body table-responsive p-0">
                <table className="table table-hover text-nowrap">
                  <thead>
                    <tr>
                      <th>No</th>
                      <th>이미지</th>
                      <th>카테고리</th>
                      <th>상품명</th>
                      <th>브랜드</th>
                      <th>가격</th>
                      <th>할인가</th>
                    </tr>
                  </thead>
                  <tbody>
                    { productList.map(product=>{
                      console.log(`http://localhost:7777/productapp/resources/p${product.prouctId}/${product.productFileList[0].fileName}`);
                      return(
                        <tr>
                            <td>{product.productId}</td>
                            <td>
                              <img src={`http://localhost:7777/productapp/resources/p${product.productId}/${product.productFileList[0].fileName}`}
                              style={{width:"100px"}}/>
                            </td>
                            <td>{product.subCategoryDTO.subName}</td>
                            <td>
                              <Link to={`/product/detail/${product.productId}`}>
                              {product.productName}
                              </Link>
                              </td>
                            <td>{product.brand}</td>
                            <td>{product.price}</td>
                            <td>{product.discount}</td>
                        </tr>
                      )})}
                    <tr>
                        <td colSpan={7}>
                            <button type="button">상품등록</button>
                            <button type="button" onClick={()=>{getProductList();}}>상품목록</button>
                        </td>
                    </tr>
                  </tbody>
                </table>
              </div>
              {/* <!-- /.card-body --> */}
            </div>
            {/* <!-- /.card --> */}
          </div>
        </div>
    )
}