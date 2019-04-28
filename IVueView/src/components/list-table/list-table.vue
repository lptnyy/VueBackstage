<template>
    <div>
		 <Table :columns="columns1" :data="data1"></Table>
	</div>
</template>
<script>
    export default {
		props:{
			columns1:{
				type: Array,
				required: true
			},
			data1:{
				type: Array,
				required: true
			}
		}
    }
</script>