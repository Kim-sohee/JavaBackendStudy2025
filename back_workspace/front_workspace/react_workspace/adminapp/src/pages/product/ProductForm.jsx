export default function ProductForm(){
    return(
        <div className="card-body">
            <div className="form-group">
            <label for="exampleInputEmail1">Email address</label>
            <input type="email" className="form-control" id="exampleInputEmail1" placeholder="Enter email"/>
            </div>
            <div className="form-group">
            <label for="exampleInputFile">File input</label>
            <div className="input-group">
                <div className="custom-file">
                <input type="file" className="custom-file-input" id="exampleInputFile"/>
                <label className="custom-file-label" for="exampleInputFile">Choose file</label>
                </div>
                <div className="input-group-append">
                <span className="input-group-text">Upload</span>
                </div>
            </div>
            </div>
            <div className="form-check">
            <input type="checkbox" className="form-check-input" id="exampleCheck1"/>
            <label className="form-check-label" for="exampleCheck1">Check me out</label>
            </div>
        </div>
    )
}