<div id="overlay">
    <div id="content">
        <button onclick="off()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback">
            <p id="sign">BRAIN SPECIALIST</p>
            <hr>
            <?php
            $brain="select * from doc_login where d_specialization='brain'";
            $exe_brain=$con->query($brain);
            $brain_special=mysqli_fetch_array($exe_brain);
            $brain_doc_id=$brain_special['d_id'];
            $brain_doc_name=$brain_special['d_name'];
            $brain_doc_experience=$brain_special['d_experience'];
            $brain_doc_age=$brain_special['age'];
            $brain_doc_number=$brain_special['d_mobile'];
            $brain_doc_gender=$brain_special['gender'];
            $brain_doc_blood=$brain_special['bloodgroup'];
            $brain_doc_address=$brain_special['address'];
            ?>
            <img id="side" src="images/cardiologist.jpg">
            <table>
                <tr>
                    <td>ID</td>
                    <td><?php echo $brain_doc_id;?></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><?php echo $brain_doc_name;?></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td><?php echo $brain_doc_experience;?></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td><?php echo $brain_doc_age;?></td>
                </tr>
                <tr>
                    <td>Mobile Number</td>
                    <td><?php echo $brain_doc_number;?></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><?php echo $brain_doc_gender;?></td>
                </tr>
                <tr>
                    <td>Blood Group</td>
                    <td><?php echo $brain_doc_blood;?></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><?php echo $brain_doc_address;?></td>
                </tr>
            </table>
            <h6></h6>
        </div>
    </div>
</div>
<script>

    function brain() {
        document.getElementById("overlay").style.display = "block";
    }

    function off() {
        document.getElementById("overlay").style.display = "none";
    }
</script>