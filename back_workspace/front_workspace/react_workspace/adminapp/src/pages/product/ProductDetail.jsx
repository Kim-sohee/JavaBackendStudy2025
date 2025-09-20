import { useEffect, useState } from "react";
import { getProduct } from "../../utils/ProductApi";
import { useNavigate, useParams } from "react-router-dom";

export default function ProductDetail(){

    const {productId}=useParams(); //추출한 변수 받기
    console.log("넘겨받은 productId=", productId);

    const navigate=useNavigate();//location.href 를 대신함 
    const [files, setFiles]=useState([]); //업로드 할 대상 배열 
    const [previews, setPreviews]=useState([]); //미리보기 할 대상 배열 

    //폼양식의 파라미터 들 
    const [formData, setFormData]=useState({
        subCategoryId:0,
        product_name:"",
        brand:"",
        price:0,
        discount:0,
        detail:""
    });

    /* 최초 한번 상세보기 요청을시도하여 화면에 반영*/
    const getDetail=async ()=>{
        const res= await getProduct(productId);
        console.log("서버에서 가져온 상세 데이터는 ", res);
        const product=res.data.result;

        setFormData({
            subCategoryId:product.subCategoryDTO.subCategoryId,
            product_name:product.produtName,
            brand:product.brand,
            price:product.price,
            discount:product.discount,
            detail:product.detail
        });
    }

    //사이드 이펙트 함수(부수효과: 최초 한번 호출시, 렌더링 할때 호출 시)
    useEffect(()=>{
        getDetail();        
    }, [productId]);



    //이전과 동일한 파일을 선택할 경우, 이벤트는 발생하지 않음    
    const handleFileChange=(e)=>{
        console.log("파일변경 이벤트 정보는 ", e);
        //사용자가 선택한 이미지들을 얻어와서, previews 배열에 반영시키자!
        //e.target.files 에는 선택한 이미지들이 유사배열 형태로 들어있다..FileList 라는 유사배열은 
        // readOnly 이므로, 수정이 불가능하다..따라서 삭제할 수 없다.. 
        //대안?  일반 배열을 선언하여 유사배열안의 요소들을 복사해놓기 
        const selectedFiles=Array.from(e.target.files);    //매개변수로 지정된 배열의 요소를 하나씩 접근하여, 새로운 배열 반환
        console.log("selectedFiles=", selectedFiles);

        
        //setFiles(미리보았던배열+지금추가로선택한배열);        
        setFiles((prev)=>[...previews, ...selectedFiles]);
      
        //이미지가 아닌것을 제외...
        //filter() 메서드(선언적 메서드)로써, 화살표함수에서 호출된 로직에 맞는 
        //새로운 배열을 반환 
        const newPreviews=selectedFiles
            .filter((file)=>file.type.startsWith("image/"))//새로운배열탄생
            .map((file)=>({
                name:file.name,  //alt 에 대입할 용도
                url:URL.createObjectURL(file)  //src에 대입할 용도
            })); //어떤 요소로 채울지 결정
        //합쳐진 배열수만큼 화면에 출력 
        //setPreviews(()=>[기존의미리보기배열 , 새롭게 합쳐진배열]);
        setPreviews((prev)=>[...prev, ...newPreviews]);
    }

    //미리보기 이미지 삭제 
    //name 매개변수는 삭제할 이미지명
    /*
        5=="5"  //true  (느슨 loose 비교)
        5==="5" //false (엄격 strict 비교)
    */
    const removePreview=(name)=>{
        //filter의 목적? 새로운 배열반환(삭제 대상이 되는 요소만 빼고서, 나머지요소들로 채우겠음)
        setPreviews((prev)=> prev.filter( (p)=>p.name !== name ));      
       
        //미리보기와 업로드 할 이미지를 동기화 
        setFiles((prev)=> prev.filter( (f)=>f.name !==name ));  
    }   

    /*서버에 파일 업로드 */
    const upload=async ()=>{
        //text 뿐 아니라, 파일을 포함한 파라미터를 전송할때는 FormData 이용할 수 있음
        //FormData 객체 안에 append() 이용하여 키-value 쌍으로 데이터 넣기 
        const sendData = new FormData();

        sendData.append("subCategoryDTO.subCategoryId", formData.subCategoryId);
        sendData.append("productName", formData.product_name);
        sendData.append("brand", formData.brand);
        sendData.append("price", formData.price);
        sendData.append("discount", formData.discount);
        sendData.append("detail", formData.detail);
        
        //바이너리 파일추가..(배열의 수만큼 반복하면서 sendData에 넣기)
        files.forEach((file)=> sendData.append("files", file)); 
        
        registProduct(sendData)
        .then( res=>{ //서버로부터 응답정보가 올경우 응답정보를 출력해보기
            console.log("서버로 부터 응답받은 정보는 ",res);  
            alert("상품이 등록되었습니다");
            navigate("/product/list");
        })
        .catch(err=>{
            console.log("에러발생", err);
        });

    }

    const handleInput = (e)=>{
        // [이벤트 발생 주체]:그 주체의 값
        setFormData({...formData, [e.target.name]:e.target.value});
    }

    return (
    <div className="card-body">
        <div className="form-group">
            <select className="form-control" name="topcategory_id" onChange={handleInput}>
                <option value="">상위 카테고리 선택</option>        
            </select>
        </div>
        <div className="form-group">                       
            <select className="form-control" name="subCategoryId"  onChange={handleInput}>
                <option value="">하위 카테고리 선택</option>        
                <option value="1">김치찌게</option>        
            </select>
        </div>
        <div className="form-group">
            <input type="text" className="form-control" name="product_name" value={formData.productName}  onChange={handleInput}/>
        </div>
        <div className="form-group">
            <input type="text" className="form-control" name="brand" value={formData.brand}  onChange={handleInput}/>
        </div>
        <div className="form-group">
            <input type="number" className="form-control" name="price" value={formData.price}  onChange={handleInput}/>
        </div>
        <div className="form-group">
            <input type="number" className="form-control" name="discount" value={formData.discount}  onChange={handleInput}/>
        </div>
        <div className="form-group">
            <textarea className="form-control" name="detail" value={formData.detail}  onChange={handleInput}></textarea>
        </div>

        <div className="form-group">
            <label for="exampleInputFile">상품 이미지를 등록하세요</label>
        <div className="input-group">
            <div className="custom-file">
                <input type="file" className="custom-file-input" id="exampleInputFile" multiple onChange={handleFileChange}/>
                <label className="custom-file-label" for="exampleInputFile">Choose file</label>
            </div>
                <div className="input-group-append">
                <span className="input-group-text">Upload</span>
            </div>
        </div>
        {/* 이미지 미리보기 영역 */}
        <div style={{display:"flex",gap:"10px", flexWrap:"wrap", marginTop:"15px"}}>
            {previews.map((p)=>(
                <div key={p.name} style={{position:"relative", display:"inline-block"}}>
                    <img src={p.url} alt={p.name} style={{width:"120px",height:"120px", objectFit:"cover",border:"1px solid #ccc",borderRadius:"5px"}}/>
                    <button style={{position:"absolute",top:"0", right:"0",background:"red", color:"white",border:"none",borderRadius:"50%",cursor:"pointer",width:"20px",height:"20px",lineHeight:"18px",padding:"0px"}} onClick={()=> removePreview(p.name) }>X</button>
                </div>
               ))
            }
        </div>
        <div className="form-group">
            <button className="form-control" onClick={upload}>업로드</button>
        </div>
        </div>
    </div>
)
}