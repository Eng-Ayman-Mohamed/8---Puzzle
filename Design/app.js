let state = "123045678";
let board;
let Maxdepth;
let Explored;
let Cost;
let endpath;
let time;
function a(){
    let Type=document.getElementById("type").value;
    let intial=document.getElementById("initial").value;

    GetData(Type,intial);
}
async function GetData(type, initial){ 
    res=await fetch(`http://localhost:8080/${type}?initialState=${initial}`,{
        method: 'GET', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, *cors, same-origin
        
        headers: {
            'Content-Type': 'application/json'
          // 'Content-Type': 'application/x-www-form-urlencoded',
        }})
    .then((resp) => resp.json())
    .then((data) => {console.log(data);
        response=data})
    .then(()=>drawTable(response))
}

async function drawTable(response){

     //after return, make the url
    if (response[0]=="failed"){
        window.alert("No Solution");
    }
    else{
        Maxdepth=response[0];
        Explored=response[1];
        Cost=response[2];
        time=response[3]
        endpath=response.slice(4);
        Update();
        
        for (let j = 0; j < endpath.length; j++) {
            intial = endpath[j];
            console.log(intial);
            console.log(typeof(intial));
    
                for(let i=0;i<9;i++){
                    board = document.getElementById(`${i}`);
                    board.innerHTML=`${intial[i]}`;
                    if(intial[i]==="0"){
                        board.innerHTML=" "
                    }
                }
                await sleep(500);
    
            }

    }
}

function sleep(ms) {
    return new Promise((resolve) => {
    setTimeout(resolve, ms);
    });
    }
function Update(){

    document.getElementById("results").style.display="block";
    document.getElementById("max").innerHTML=`${Maxdepth}`;
    document.getElementById("explored").innerHTML=`${Explored}`;
    document.getElementById("cost").innerHTML=`${Cost}`;
    document.getElementById("time").innerHTML=`${time}`;
}