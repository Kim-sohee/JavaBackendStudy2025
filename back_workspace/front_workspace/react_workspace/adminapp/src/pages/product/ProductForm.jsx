import { useState } from "react";
import { registProduct } from "../../utils/ProductApi";
import { useNavigate } from "react-router-dom";

export default function ProductForm(){
    const navigate = useNavigate();     //location.href를 대신함
    const [files, setFiles]=useState([]);
    const [previews, setPreviews]=useState([]);

    //폼 양식의 파라미터들
    const [formData, setFormData] = useState({
        subCategoryId: 0,
        product_name: "",
        brand: "",
        price: 0,
        discount: 0,
        detail: ""
    });

    //이전과 동일한 파일을 선택할 경우, 이벤트는 발생하지 않음
    const handleFileChange=(e)=>{
        console.log("파일변경 이벤트 정보는 ", e);

        //사용자가 선택한 이미지들을 얻어와서, previews 배열에 반영시키자
        //e.target.files에는 선택한 이미지들이 유사배열 형태로 들어있다. 
        //FileList라는 유사배열은 readOnly 이므로, 수정이 불가능하다.
        //따라서 삭제할 수 없다.
        //대안: 일반 배열을 선언하여 유사배열안의 요소들을 복사해놓기
        const selectedFiles = Array.from(e.target.files);   //매개변수로 지정된 배열의 요소를 하나씩 접근하여, 새로운 배열 반환

        //setFiles(미리보았던 배열 + 지금 추가로 선택한 배열)
        setFiles((prev)=>[...previews, ...selectedFiles]);
        const newPreviews = selectedFiles
                .filter((file)=>file.type.startsWith("image/"))
                .map((file)=>({
                    name: file.name,
                    url: URL.createObjectURL(file)
                }));

        //합쳐진 배열수만큼 화면에 출력
        setPreviews((prev)=>[...prev, ...newPreviews]);
    }

    //미리보기 이미지 삭제
    const removePreview=(name)=>{
        setPreviews((prev)=> prev.filter(x=> x.name !== name));
        //미리보기와 업로드 할 이미지를 동기화
        setFiles((prev)=> prev.filter(x=> x.name !== name));
    }

    //서버에 파일 업로드
    const upload=async()=>{
        //text 뿐 아니라, 파일을 포함한 파라미터를 전송할 때는 FormData 이용할 수 있음
        //FormData 객체 안에 append() 이용하여 key-value 형식으로 데이터 넣기
        const sendData = new FormData();
        sendData.append("subCategoryDTO.subCategoryId", formData.subCategoryId);
        sendData.append("productName", formData.product_name);
        sendData.append("brand", formData.brand);
        sendData.append("price", formData.price);
        sendData.append("discount", formData.discount);
        sendData.append("detail", formData.detail);

        //바이너리 파일 추가(배열의 수만큼 반복하면서 sendData에 넣기)
        files.forEach((file)=>sendData.append("files",file));

        registProduct(sendData)
        .then(res=>{        //서버로부터 응답 정보가 올 경우, 응답 정보 출력하기
            console.log("서버로부터 응답받은 정보는 ", res);
            alert("상품이 등록되었습니다");
            navigate("/product/list");
        })
        .catch(err=>{
            console.log("에러발생", err);
        });
    }

    const handleInput=(e)=>{
        //[이벤트 발생 주체] : 그 주체의 값
        // console.log(e);
        setFormData({...formData, [e.target.name]: e.target.value});
    }

    return(
        <div className="card-body">
            <label for="exampleInputFile">상품정보 등록</label>
            <div className="form-group">
                <select className="form-control" name="topcategoryId" onChange={handleInput}>
                    <option value="">상위 카테고리 선택</option>
                </select>
            </div>
            <div className="form-group">
                <select className="form-control" name="subCategoryId" onChange={handleInput}>
                    <option value="0">하위 카테고리 선택</option>
                    <option value="1">운동화</option>
                </select>
            </div>
            <div className="form-group">
                <input type="text" className="form-control" name="product_name" placeholder="상품명" onChange={handleInput}/>
            </div>
            <div className="form-group">
                <input type="text" className="form-control" name="brand" placeholder="브랜드" onChange={handleInput}/>
            </div>
            <div className="form-group">
                <input type="number" className="form-control" name="price" placeholder="가격" onChange={handleInput}/>
            </div>
            <div className="form-group">
                <input type="number" className="form-control" name="discount" placeholder="할인가" onChange={handleInput}/>
            </div>
            <div className="form-group">
                <textarea className="form-control" name="detail" placeholder="상세설명" onChange={handleInput}></textarea>
            </div>

            <div className="form-group">
                <label for="exampleInputFile">상품이미지 등록</label>
                <div className="input-group">
                    <div className="custom-file">
                        <input type="file" className="custom-file-input" id="exampleInputFile" multiple onChange={handleFileChange}/>
                        <label className="custom-file-label" for="exampleInputFile">Choose file</label>
                    </div>
                    <div className="input-group-append">
                        <span className="input-group-text">Upload</span>
                    </div>
                </div>
            </div>
            {/* 이미지 미리보기 영역 */}
            <div style={{display:"flex", gap:"10px", flexWrap:"wrap", marginTop:"15px"}}>
                {previews.map((p)=>(
                <div key={p.name} style={{position:"relative", display:"inline-block"}}>
                    <img src={p.url} alt={p.name} style={{width:"120px", height:"120px", objectFit:"cover", border:"1px solid #ccc", borderRadius:"5px"}}/>
                    <button onClick={()=>removePreview(p.name)} style={{position:"absolute", top:"0px", right:"0px", background:"red", color:"white", border:"none", borderRadius:"50%", cursor:"pointer", width:"20px", height:"20px", lineHeight:"18px", padding:0}}>X</button>
                </div>
                ))}
            </div>
            <div className="form-group">
                <button className="form-control" onClick={upload} style={{marginTop:"10px"}}>상품 업로드</button>
            </div>
        </div>
    )
}