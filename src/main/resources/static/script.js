async function createTransaction() {
    console.log("Button clicked");
    const customerName =
        document.getElementById(
            "customerName"
        ).value;

    const amount = parseFloat(
        document.getElementById(
            "amount"
        ).value
    );

    const state =
        document.getElementById(
            "state"
        ).value;

    const transaction = {

        customerName,
        amount,
        state
    };

    console.log(transaction);

    const response = await fetch(
        "/transactions",
        {

            method: "POST",

            headers: {
                "Content-Type":
                    "application/json"
            },

            body: JSON.stringify(
                transaction
            )
        }
    );

    const savedTransaction =
        await response.json();

    console.log(savedTransaction);

    loadTransactions();
}

async function loadTransactions() {

    const response =
        await fetch("/transactions");

    const transactions =
        await response.json();

    console.log(transactions);

    const table =
        document.getElementById(
            "transactionTable"
        );

    table.innerHTML = "";

    transactions.forEach(
        transaction => {

            table.innerHTML += `

            <tr>

                <td>${transaction.id}</td>

                <td>
                    ${transaction.customerName}
                </td>

                <td>
                    ${transaction.amount}
                </td>

                <td>
                    ${transaction.state}
                </td>

                <td>
                    ${transaction.riskScore}
                </td>

                <td>
                    ${transaction.status}
                </td>

            </tr>
            `;
        }
    );
}

loadTransactions();