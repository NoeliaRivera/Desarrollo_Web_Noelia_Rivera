document.addEventListener("DOMContentLoaded", () => {

    fetch("/api/conteoMiembros")
        .then(response => response.json())
        .then(data => {
            Highcharts.chart('gLinea', {
                title: { text: 'Miembros registrados por día' },
                xAxis: { categories: data.categorias },
                yAxis: { title: { text: 'Cantidad de miembros' } },
                series: [{
                    name: 'Miembros',
                    data: data.valores
                }]
            });
        });

    fetch("/api/conteoActividades")
        .then(response => response.json())
        .then(data => {
            Highcharts.chart('gTorta', {
                chart: { type: 'pie' },
                title: { text: 'Actividades por tipo' },
                series: [{
                    name: 'Cantidad',
                    data: data
                }]
            });
        });

    fetch("/api/conteoComunas")
        .then(response => response.json())
        .then(data => {
            Highcharts.chart('gBarras', {
                chart: { type: 'column' },
                title: { text: 'Actividades por comuna' },
                xAxis: { categories: data.comunas },
                yAxis: { title: { text: 'Cantidad de actividades' } },
                series: [{
                    name: 'Actividades',
                    data: data.cantidades
                }]
            });
        });

});