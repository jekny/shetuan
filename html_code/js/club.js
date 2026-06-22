async function loadClubList(){
    let res = await axios.get(`${BASE_URL}/club/list`);
    let box = document.getElementById("clubBox");
    box.innerHTML = "";
    res.data.data.forEach(club=>{
        box.innerHTML += `
            <div class="col-3 mb-3">
                <div class="card club-card p-3">
                    <h5>${club.clubName}</h5>
                    <p>${club.intro}</p>
                </div>
            </div>
        `
    })
}