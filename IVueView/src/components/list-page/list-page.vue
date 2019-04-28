<template>
    <Page :total="total" @on-change="onChange" show-total />
</template>
<script>
    export default {
        props:{
        	total:{
        		type: Number,
        		required: true
        	}
        },
		methods:{
			onChange(index){
				this.$emit('pageHandle', index);
			}
		}
    }
</script>